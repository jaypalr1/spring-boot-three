package com.jay.lombok.accessor;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.experimental.PackagePrivate;
import lombok.extern.slf4j.Slf4j;

/*
This feature does not work on its own but is used to configure how @Getter and @Setter annotations generate
new methods. It has three flags that configure its behavior:

chain: Makes setters return 'this' reference instead of 'void'

fluent: Creates fluent interface. This names all getters and setters 'name' instead of 'getName' and 'setName'.
  It also sets 'chain' to true unless specified otherwise.

prefix: Some developers prefer to start field names with a prefix like “f”.
This annotation allows to drop the specified prefix from getters and setters to avoid method names
like 'fName' or 'getFName'.
*/

@Data
@Slf4j
@Getter
@Setter
@Accessors(fluent = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FlexibleTest {

  @NonFinal
  @PackagePrivate
  String name;

  @NonFinal
  int age;

  public static void main(String[] args) {
    FlexibleTest person = new FlexibleTest()
        .name("John")
        .age(34);

    log.info("{}", person);
  }
}
