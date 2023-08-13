package com.jay.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class AopAspect {

  @Before("@annotation(com.jay.aop.annotation.AopAnnotation)")
  public void testBefore() {
    log.info("Executing Before");
  }

  @After("@annotation(com.jay.aop.annotation.AopAnnotation)")
  public void testAfter() {
    log.info("Executing After");
  }
}
