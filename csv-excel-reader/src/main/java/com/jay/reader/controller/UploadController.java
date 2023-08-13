package com.jay.reader.controller;

import com.jay.reader.service.UploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UploadController {

  private final UploadService uploadService;

  @PostMapping(value = "/upload")
  public ResponseEntity<String> uploadCsvFile2(@RequestParam MultipartFile file) {
    return new ResponseEntity<>(uploadService.uploadFile(file), HttpStatus.OK);
  }

//  @GetMapping(value = "/read")
//  public ResponseEntity<String> read2() {
//    return new ResponseEntity<>(uploadService.readRecord(), HttpStatus.OK);
//  }
}
