package com.jay.reader.service;

import org.springframework.web.multipart.MultipartFile;

public interface Parser {

  Boolean checkFileType(MultipartFile type);

  void parse(MultipartFile file);
}
