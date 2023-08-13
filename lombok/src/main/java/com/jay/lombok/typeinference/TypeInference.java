package com.jay.lombok.typeinference;

import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@Slf4j
public class TypeInference {

  public static void main(String[] args) {
    typeInferenceTest();
  }

  public static void typeInferenceTest() {
    val list = new ArrayList<String>();

    list.add("foo");
    for (val item : list) {
      log.info(item);
    }
  }
}
