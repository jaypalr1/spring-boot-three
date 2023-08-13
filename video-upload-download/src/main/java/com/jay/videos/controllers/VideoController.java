package com.jay.videos.controllers;

import com.jay.videos.services.VideoService;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
@RequestMapping("/video")
public class VideoController {

  private final VideoService videoService;

  // Each parameter annotated with @RequestParam corresponds to a form field where the String argument is the name of the field
  @PostMapping("/save")
  public ResponseEntity<String> saveVideo(
      @RequestParam("file") MultipartFile file, @RequestParam("name") String name)
      throws IOException {

    return ResponseEntity.ok(videoService.saveVideo(file, name));
  }

  // {name} is a path variable in the url. It is extracted as the String parameter annotated with @PathVariable
  @GetMapping("/download/{name}")
  public ResponseEntity<Resource> getVideoByName(@PathVariable String name) {
    return ResponseEntity
        .status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_OCTET_STREAM)
        .body(new ByteArrayResource(videoService.getVideo(name).getData()));
  }

  @GetMapping("/all")
  public ResponseEntity<List<String>> getAllVideoNames() {
    return ResponseEntity.ok(videoService.getAllVideoNames());
  }
}
