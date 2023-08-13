package com.jay.stream.config;

import com.jay.stream.service.StreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Configuration
public class FunctionalEndPointConfig {

  @Autowired
  private StreamService service;

  @Bean
  public RouterFunction<ServerResponse> router() {
    return RouterFunctions
        .route()
        .GET("functional/video/{title}", this::videoHandler)
        .build();
  }

  private Mono<ServerResponse> videoHandler(ServerRequest serverRequest) {
    String title = serverRequest.pathVariable("title");

    return ServerResponse
        .ok()
        .contentType(MediaType.valueOf("video/mp4"))
        .body(service.getVideo(title), Resource.class);
  }
}
