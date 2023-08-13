package com.jay.lombok.sneakythrow;

import java.io.IOException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/*
It allows you to throw checked exceptions without using the 'throws' declaration.
A drawback of this annotation is that you cannot catch a checked exception that is not declared.
The main method code will not compile
*/

@Slf4j
public class SneakyRunnable {

  @SneakyThrows(IOException.class)
  public static void run() {
    throw new IOException();
  }

  public static void main(String[] args) {
    try {
      run();
    }
// This code will not compile as IOException is never thrown.
//    catch (IOException ex) {
    // javac: exception java.lang.IOException is never thrown in body of corresponding try statement
//      log.info("", ex);
//    }
    catch (Exception ex) {
      // javac: exception java.lang.InterruptedException is never thrown in body of corresponding try statement
      log.info("", ex);
    }
  }
}
