package com.jay.job;

/*
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class DemoJob {

  private final JobRepository jobRepository;
  private final PlatformTransactionManager platformTransactionManager;

  @Bean
  public Job demoJobOne() {
    return new JobBuilder("demoJobOne", jobRepository)
//        .preventRestart()
        .incrementer(new RunIdIncrementer())
        .listener(new JobExecutionListener() {
          @Override
          public void beforeJob(JobExecution jobExecution) {
            if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
              log.info("Job already completed.");
            }

            log.info("Before batch processing.");
          }

          @Override
          public void afterJob(JobExecution jobExecution) {
            log.info("After batch processing.");

            if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
              log.info("Job completed.");
            }
          }
        })
        .start(new StepBuilder("demoStep1", jobRepository)
            .tasklet((StepContribution contribution, ChunkContext chunkContext) -> {
              System.out.println("Inside tasklet.");
              return null;
            })
            .transactionManager(platformTransactionManager)
            .taskExecutor(taskExecutor())
            .build()
        )
        .build();
  }

  @Bean
  public TaskExecutor taskExecutor() {
    ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();

    taskExecutor.setAllowCoreThreadTimeOut(true);
    taskExecutor.setCorePoolSize(5);

    taskExecutor.setMaxPoolSize(10);
    taskExecutor.setQueueCapacity(1000);

    taskExecutor.setThreadNamePrefix("Batch Thread -> : ");
    taskExecutor.afterPropertiesSet();

    return taskExecutor;
  }
}
*/
