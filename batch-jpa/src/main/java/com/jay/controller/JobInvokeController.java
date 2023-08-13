package com.jay.controller;

import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class JobInvokeController {

  private final JobLauncher jobLauncher;
  private final Job demoJob;

  @GetMapping("/")
  public void invokeBatch() throws Exception {
    log.info("Request received to invoke the job");

    JobParametersBuilder builder = new JobParametersBuilder();
    builder.addDate("date", new Date());

    var jobExecution = jobLauncher.run(demoJob, builder.toJobParameters());

    log.info("{}", jobExecution.getStatus());
  }
}
