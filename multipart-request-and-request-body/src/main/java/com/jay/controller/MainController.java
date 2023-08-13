package com.jay.controller;

import com.jay.dto.DummyDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MainController {

  @PostMapping(value = "/post")
  public String test(@RequestPart("multipartRequest") MultipartFile multipartRequest,
      @RequestPart("dummyDto") DummyDto dummyDto) {
    log.info("Request received;");

    return "Successfully done.";
  }
}
