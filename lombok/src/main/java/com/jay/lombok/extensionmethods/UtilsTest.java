package com.jay.lombok.extensionmethods;

import java.util.Arrays;
import java.util.List;
import lombok.experimental.ExtensionMethod;
import lombok.extern.slf4j.Slf4j;

/*
Almost every Java project has so-called utility classes – classes that contain only static methods. Often,
their authors would prefer the methods were part of the interface they relate to. Methods in a StringUtils class,
for example, operate on strings, and it would be nice if they could be called directly on String instances.
*/

@Slf4j
@ExtensionMethod({StringUtils.class, Arrays.class})
public class UtilsTest {

  public static void main(String[] args) {
    String s = null;
    String s2 = "str";

//    Can not be instantiated as access is private
//    new StringUtils();

    log.info("{}", s.isNullOrEmpty());
    log.info("{}", s2.isNullOrEmpty());

    String[] arr = new String[]{"foo", "bar", "baz"};
    List<String> list = arr.asList();

    for (String s1 : list) {
      log.info("{}", s1);
    }
  }
}
