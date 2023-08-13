package com.jay.stream.controller;

import com.jay.stream.service.StreamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MainController {

  private final StreamService streamService;

  @GetMapping(value = "video/{title}", produces = {"video/mp4", "video/mkv", "video/ts"})
  public Mono<Resource> getVideos(@PathVariable String title) {
    return streamService.getVideo(title);
  }
}
