package com.jay.aop.controller;

import com.jay.aop.service.AopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AopController {

  private final AopService aopService;

  @GetMapping("/aop")
  public void aopDemo() {
    aopService.testAop();
  }
}
