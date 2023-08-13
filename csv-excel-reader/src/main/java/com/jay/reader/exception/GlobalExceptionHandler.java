package com.jay.reader.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleGenericException(Exception e) {
    log.error("Generic Exception: ", e);
    return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(MultipartException.class)
  public ResponseEntity<String> handleMultipartException(MultipartException e) {
    log.error(e.getMessage(), e);
    return new ResponseEntity<>("File not uploaded", HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(MaxUploadSizeExceededException.class)
  public ResponseEntity<String> handleMaxSizeException(MaxUploadSizeExceededException e) {
    log.error("File too large: ", e);
    return new ResponseEntity<>("File too large!", HttpStatus.EXPECTATION_FAILED);
  }
}
