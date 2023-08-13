package com.jay.scheduler.service;

import java.util.concurrent.TimeUnit;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableAsync
@Configuration
@EnableScheduling
public class TestScheduler {

  private long counter = 0;

  //Executes only after the completion of previous task
  @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.SECONDS)
  public void testSchedule() {
    counter++;
    System.out.println("Fixed delay task - " + counter);
  }

  @Async //Ensure parallel execution
  @Scheduled(fixedRate = 1, timeUnit = TimeUnit.SECONDS)
  // Executes irrespective of previous task but need to add async else it'll act like fixedDelay
  public void scheduleFixedRateTask() throws InterruptedException {
    counter++;
    System.out.println("Fixed rate task - " + counter);
    Thread.sleep(20000);
  }
}
