package com.jay;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableBatchProcessing // Breaks invoking of job automatically
@SpringBootApplication
public class BatchJpaApplication {

  public static void main(String[] args) {
    SpringApplication.run(BatchJpaApplication.class, args);
  }
}
