package com.jay.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class AopSecondAspect {

  @Before("@annotation(com.jay.aop.annotation.AopAnnotation)")
  public void testSecond() {
    log.info("Inside Second AOP.");
  }
}
