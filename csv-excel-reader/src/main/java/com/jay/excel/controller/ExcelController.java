package com.jay.excel.controller;

import com.jay.excel.service.ExcelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/excel")
public class ExcelController {

  private final ExcelService excelService;

  @GetMapping("/poiji")
  public String saveExcelWithPoi() {
    return excelService.readExcelWithPoiji();
  }

  @GetMapping("/poiji/inputstream")
  public String readExcelWithPoiInputStream() {
    return excelService.readExcelWithPoijiInputStream();
  }

  @GetMapping("/apache/poi")
  public String readExcelWithApachePoi() {
    return excelService.readExcelWithApachePoi();
  }
}
