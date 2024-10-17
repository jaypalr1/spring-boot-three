package com.jay.controller;

import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxFolder;
import com.box.sdk.BoxItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/box")
public class BoxController {

  private final BoxAPIConnection boxAPIConnection;

  @GetMapping
  public String box() {
    try {
      BoxFolder boxFolder = new BoxFolder(boxAPIConnection, "276140286923");

      for (BoxItem.Info info : boxFolder) {
        if (info instanceof BoxItem.Info info1) {
          System.out.println(info);
        }
      }
    } catch (Exception e) {
      log.error(e.getMessage());
    }

    return "Hello World";
  }
}
