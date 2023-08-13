package com.jay.stream.service;

import org.springframework.core.io.Resource;
import reactor.core.publisher.Mono;

public interface StreamService {

  Mono<Resource> getVideo(String title);
}
