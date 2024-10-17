package com.jay.task;

import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
class TestTaskScheduler {

  // @Scheduled(cron = "0 0/15 * * * ?", fixedDelay = 1)
  @Scheduled(fixedDelay = 2, timeUnit = TimeUnit.SECONDS)
  @SchedulerLock(name = "Task_Name_in_MS_Architecture", lockAtLeastFor = "PT5M", lockAtMostFor = "PT14M")
  public void scheduledTask() {

    log.info("Testing");
  }

  private synchronized void getDbObject() {
//    Make DB call here. Get the object. Set to in-progress and proceed with processing. No other
//    object will be able to pick it up from there.
  }
}
