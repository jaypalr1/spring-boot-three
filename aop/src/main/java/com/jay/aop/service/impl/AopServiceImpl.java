package com.jay.aop.service.impl;

import com.jay.aop.annotation.AopAnnotation;
import com.jay.aop.service.AopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AopServiceImpl implements AopService {

  @Override
  @AopAnnotation
  public void testAop() {
    log.info("Inside AOP Service.");
  }
}
