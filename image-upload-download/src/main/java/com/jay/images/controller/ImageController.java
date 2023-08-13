package com.jay.images.controller;

import com.jay.images.service.ImageService;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {

  private final ImageService imageService;

  @PostMapping("/upload")
  public ResponseEntity<String> upload(@RequestParam("imageFile") MultipartFile multipartFile) {
    return ResponseEntity.status(HttpStatus.OK).body(imageService.uploadImage(multipartFile));
  }

  @GetMapping(path = {"/download/{imageName}"})
  public void download(@PathVariable String imageName, HttpServletResponse response) {
    imageService.download(imageName, response);
  }

/*
  // compress the image bytes before storing it in the database
  private static byte[] compressBytes(byte[] data) {
    Deflater deflater = new Deflater();
    deflater.setInput(data);
    deflater.finish();

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);

    byte[] buffer = new byte[1024];

    while (!deflater.finished()) {
      int count = deflater.deflate(buffer);

      outputStream.write(buffer, 0, count);
    }

    try {
      outputStream.close();
    } catch (IOException e) {
    }

    log.info("Compressed Image Byte Size - {}", outputStream.toByteArray().length);

    return outputStream.toByteArray();
  }

  // uncompress the image bytes before returning it to the angular application
  private static byte[] decompressBytes(byte[] data) {
    Inflater inflater = new Inflater();
    inflater.setInput(data);

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);

    byte[] buffer = new byte[1024];
    int count = 0;

    try {
      while (!inflater.finished()) {
        count = inflater.inflate(data);

        outputStream.write(data, 0, count);

        inflater.end();

        outputStream.close();
      }
    } catch (IOException e) {
      log.error("IO Error", e);
    } catch (DataFormatException e) {
      log.error("DataFormat Exception", e);
    }

    return outputStream.toByteArray();
  }*/
}
