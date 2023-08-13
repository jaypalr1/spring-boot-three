package com.jay.spring;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class SpringGracefulApp {

  private static final String DASH = "============================================================";

  public static void main(String[] args) {
//    SpringApplication.run(SpringApp.class, args);
//
//    Thread shoutDownHook = new Thread(() -> {
//      System.out.println(DASH);
//      log.info("Terminating JVM");
//      System.out.println(DASH);
//    });
//
//    Runtime.getRuntime().addShutdownHook(shoutDownHook);

    ApplicationContext context = SpringApplication.run(SpringGracefulApp.class, args);
    MyBean myBean = context.getBean(MyBean.class);
    myBean.doSomething();
  }

  @Bean
  MyBean myBean() {
    return new MyBean();
  }

  private static class MyBean {

    @PostConstruct
    public void init() {
      System.out.println("init");
    }

    public void doSomething() {
      System.out.println("in doSomething()");
    }

    @PreDestroy
    public void destroy() {
      System.out.println("destroy");
    }
  }
}
