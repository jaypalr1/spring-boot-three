package com.jay.stream.service.impl;

import com.jay.stream.service.StreamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class StreamServiceImpl implements StreamService {

  private final ResourceLoader resourceLoader;

  @Value("${video.path}")
  private String format;

  @Override
  public Mono<Resource> getVideo(String title) {
    return Mono.fromSupplier(() -> resourceLoader.getResource(String.format(format, title)));
  }
}
