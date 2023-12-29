package com.jay.csv.controller;

import com.jay.csv.service.CsvService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/csv")
public class CsvController {

  private final CsvService csvService;

  @GetMapping("/open-csv")
  public String readCsvWithOpenCsv() {
    return csvService.readCsvWithOpenCsv();
  }

  @GetMapping("/apache-csv")
  public String readCsvWithApacheCsv() {
    return csvService.readCsvWithApacheCsv();
  }
}
