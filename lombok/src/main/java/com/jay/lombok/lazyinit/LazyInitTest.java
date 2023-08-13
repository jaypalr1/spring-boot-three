package com.jay.lombok.lazyinit;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LazyInitTest {

  public static void main(String[] args) {
    LazyGettersEx lazyGettersEx = new LazyGettersEx();

    log.info("DeepThought is ready");
    log.info("{}", lazyGettersEx.getTheAnswer());
  }
}
