package com.jay.camel;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.main.BaseMainSupport;
import org.apache.camel.main.Main;
import org.apache.camel.main.MainListenerSupport;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class CamelGracefulApp {

  private static final String DASH = "============================================================";

  public static void main(String[] args) {
    log.info("\n{}\n App startup initiated \n{}", DASH, DASH);

    Main main = new Main(CamelGracefulApp.class);
    main.addMainListener(new MainListenerSupport() {

      @Override
      public void afterStart(BaseMainSupport mainSupport) {
        log.info("\n{}\n Startup complete \n{}", DASH, DASH);
      }

      @Override
      public void afterStop(BaseMainSupport mainSupport) {
        log.info("\n{}\n JVM Terminated \n{}", DASH, DASH);
      }
    });

    try {
      main.run();
    } catch (Exception e) {
      log.error("Startup failed", e);
    }
  }
}
