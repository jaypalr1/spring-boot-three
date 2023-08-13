package com.jay.lombok.nullchecker;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NullCheckerTest {

  public static void main(String[] args) {
    User user = new User(null, null, 0);
    user.setNameAndSurname(null, null);

    log.info("{}", user);
  }
}
