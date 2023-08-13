package com.jay.pdf.controller;

import com.jay.pdf.service.CityService;
import com.jay.pdf.utils.GeneratePdfReport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/pdf")
@RequiredArgsConstructor
public class PdfController {

  private final CityService cityService;

  @GetMapping(value = "/report", produces = MediaType.APPLICATION_PDF_VALUE)
  public ResponseEntity<InputStreamResource> citiesReport() {
    return new ResponseEntity<>(
        GeneratePdfReport.citiesReport(cityService.findAll()), HttpStatus.OK);
  }
}
