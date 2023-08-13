package com.jay.job;

import com.jay.batch.listener.PersonJobListener;
import com.jay.batch.processor.PersonProcessor;
import com.jay.dto.PersonDto;
import com.jay.entity.PersonEntity;
import javax.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class Demo {

  private final JobRepository jobRepository;
  private final PlatformTransactionManager platformTransactionManager;
  private final EntityManagerFactory entityManagerFactory;

  private final PersonJobListener personJobListener;
  private final PersonProcessor personProcessor;

  @Bean
  public Job demoJob() {
    return new JobBuilder("demoJob", jobRepository)
        .preventRestart()
        .incrementer(new RunIdIncrementer())
        .listener(personJobListener)
        .flow(stepsDemo())
        .end()
        .build();
  }

  public Step stepsDemo() {
    return new StepBuilder("stepsDemo", jobRepository)
        .<PersonDto, PersonEntity>chunk(500, platformTransactionManager)
//        .allowStartIfComplete(true)
        .reader(reader())
        .processor(personProcessor)
        .writer(writer())
        .taskExecutor(taskExecutor())
        .build();
  }

  public ItemReader<PersonDto> reader() {
    return new FlatFileItemReaderBuilder<PersonDto>()
        .name("PersonFileReader")
        .resource(new ClassPathResource("sample-data.csv"))
        .delimited().delimiter(",")
        .names("name", "lastName")
//        .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
//          setTargetType(PersonDto.class);
//        }})
        .targetType(PersonDto.class)
        .build();
  }

  public ItemWriter<PersonEntity> writer() {
    return new JpaItemWriterBuilder<PersonEntity>()
        .entityManagerFactory(entityManagerFactory)
        .build();
  }

  public TaskExecutor taskExecutor() {
    ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();

    taskExecutor.setCorePoolSize(5);

    taskExecutor.setMaxPoolSize(10);

    taskExecutor.setQueueCapacity(1000);

    taskExecutor.setThreadNamePrefix("Batch Thread -> : ");
    taskExecutor.afterPropertiesSet();
    return taskExecutor;
  }
}
