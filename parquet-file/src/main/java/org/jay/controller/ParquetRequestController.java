package org.jay.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jay.random.Test;
import org.jay.random.dto.DataList;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ParquetRequestController {

  private final Test test;

  @PostMapping("/random")
  public String testTest(@RequestBody DataList dataList) {
    return test.testParquet(dataList);
  }
}
