package com.jay.lombok.lazyinit;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/*
Lazy initialization is an optimization technique that is used to delay a costly field initialization.
To use this annotation, we need to define a private and final field and assign a result of a function call to it:
*/

@Slf4j
@ToString
public class LazyGettersEx {

  @Getter(lazy = true)
  private final String theAnswer = calculateTheUltimateAnswer();

  private String test;

  public LazyGettersEx() {
    log.info("Building DeepThought");
  }

  private String calculateTheUltimateAnswer() {
    log.info("Thinking for 7.5 million years");
    return "42";
  }
}
