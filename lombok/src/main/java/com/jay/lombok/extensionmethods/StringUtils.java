package com.jay.lombok.extensionmethods;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StringUtils {

//  private StringUtils() {
//    throw new UnsupportedOperationException("Utility class should not be instantiated");
//  }

// would be nice to call "abc".isNullOrEmpty() instead of StringUtils.isNullOrEmpty("abc")
  public static boolean isNullOrEmpty(String str) {
    return str == null || str.isEmpty();
  }
}
