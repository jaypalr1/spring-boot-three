package com.jay.reader.service.impl;

import com.jay.reader.service.Parser;
import com.jay.reader.service.UploadService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class UploadServiceImpl implements UploadService {

  private final List<Parser> parsers;

  public String uploadFile(MultipartFile file) {

    parsers.stream()
        .filter(p -> p.checkFileType(file))
        .findFirst()
        .ifPresentOrElse(p -> p.parse(file), () -> {
          throw new AssertionError("File type not supported");
        });

    return "Successfully read the file";
  }

  public String readRecord() {
    return null;
  }
}
