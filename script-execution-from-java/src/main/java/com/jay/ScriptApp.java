package com.jay;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ScriptApp {

  public static void main(String[] args) throws InterruptedException {

    String[] command = {"fileMove.bat", "complete file path", "destination dir"};

    try {
      Process process = Runtime.getRuntime().exec(command);

      int exitVal = process.waitFor();

      if (exitVal == 0) {
        log.info("Execution was successful.");
      } else {
        log.error("Error occurred while executing the script.");
      }
    } catch (SecurityException | IOException | NullPointerException | IllegalArgumentException e) {
      log.error("Error occurred while executing the command");
    }
  }
}
