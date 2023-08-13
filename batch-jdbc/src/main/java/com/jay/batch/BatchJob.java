package com.jay.batch;

import com.jay.dto.Person;
import com.jay.processor.PersonItemProcessor;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.MethodInvokingTaskletAdapter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class BatchJob {

  private final PersonItemProcessor personItemProcessor;
  private final DataSource dataSource;
  private final JobRepository jobRepository;
  private final JobCompletionNotificationListener jobCompletionNotificationListener;
  private final PlatformTransactionManager platformTransactionManager;

  @Bean
  public Job importUserJob() {
    return new JobBuilder("importUserJob", jobRepository)
        .incrementer(new RunIdIncrementer())
        .listener(jobCompletionNotificationListener)
        .validator(new DefaultJobParametersValidator() {

          @Override
          public void validate(JobParameters jobParameters) {
            log.info("Validating Job params.");
          }
        })
        .start(new StepBuilder("taskletStep", jobRepository)
            .tasklet((stepContribution, chunkContext) -> {
              MethodInvokingTaskletAdapter methodInvokingTaskletAdapter = new MethodInvokingTaskletAdapter();
              methodInvokingTaskletAdapter.setTargetObject(personItemProcessor);
              methodInvokingTaskletAdapter.setTargetMethod("process");
              methodInvokingTaskletAdapter.setArguments(new String[]{"", ""});

              return RepeatStatus.FINISHED;
            }, platformTransactionManager)
            .build()
        )
        .build();
  }

  public Step step() {
    return new StepBuilder("step", jobRepository)
        .<Person, Person>chunk(10)
        .reader(reader())
        .processor(personItemProcessor)
        .writer(writer())
        .faultTolerant()
        .skip(RuntimeException.class)
        .skipLimit(2)
        .noSkip(Exception.class)
        .retryLimit(2)
        .retry(RuntimeException.class)
        .noRollback(ValidationException.class)
        .taskExecutor(taskExecutor())
        .build();
  }

  public FlatFileItemReader<Person> reader() {
    return new FlatFileItemReaderBuilder<Person>()
        .name("personItemReader")
        .resource(new ClassPathResource("sample-data.csv"))
        .delimited()
        .names("firstName", "lastName")
        .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
          setTargetType(Person.class);
        }})
        .build();
  }

  public JdbcBatchItemWriter<Person> writer() {
    return new JdbcBatchItemWriterBuilder<Person>()
        .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
        .sql("INSERT INTO people (first_name, last_name) VALUES (:firstName, :lastName)")
        .dataSource(dataSource)
        .build();
  }

  public TaskExecutor taskExecutor() {
    ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();

    taskExecutor.setCorePoolSize(5);

    taskExecutor.setMaxPoolSize(10);

    taskExecutor.setQueueCapacity(100);

    taskExecutor.setThreadNamePrefix("Batch Thread -> : ");
    taskExecutor.afterPropertiesSet();
    return taskExecutor;
  }
}
