package org.jay.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jay.dto.DynamicRequestDto;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DynamicJsonController {

  @PostMapping("test")
  public String readDynamicJson(@RequestBody DynamicRequestDto dynamicRequestDto) {
    dynamicRequestDto.getFields().forEach((k, v) -> System.out.println(k + " and " + v));

    Object c = dynamicRequestDto.getFields().get("c");

    return "Success";
  }

  @ExceptionHandler(Exception.class)
  public String exceptionHandler(Exception exception) {
    log.error("", exception);

    return "Failed";
  }
}
