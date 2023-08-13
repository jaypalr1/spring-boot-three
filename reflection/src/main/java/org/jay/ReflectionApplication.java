package org.jay;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SuppressWarnings({"squid:S106", "squid:S3011"})
public class ReflectionApplication {

  public static void main(String[] args) throws Exception {
    com.jay.reflection.Cat cat = new com.jay.reflection.Cat("CatName", 1);

    Field[] catFields = cat.getClass().getDeclaredFields();

    // Class member access
    System.out.println("\nAll members variables:");
    Arrays.asList(catFields).forEach(System.out::println);

    System.out.println("\nAccessing private final member of class:");
    for (Field field : catFields) {
      if ("name".equalsIgnoreCase(field.getName())) {
        field.setAccessible(true); // Need to set this property to true.
        field.set(cat, "New Name");
      }
    }

    System.out.println("New cat name:: " + cat.getName() + "\n");

    // Method access
    Method[] catMethods = cat.getClass().getDeclaredMethods();

    System.out.println("All methods of class:");
    Arrays.asList(catMethods).forEach(System.out::println);

    System.out.println("\nAccessing private method of class: ");
    for (Method method : catMethods) {
      if ("privateMethod".equalsIgnoreCase(method.getName())) {
        method.setAccessible(true);
        method.invoke(cat);
      }
    }

    System.out.println("\nAccessing private STATIC method of class: ");
    for (Method method : catMethods) {
      if ("privateStaticMethod".equalsIgnoreCase(method.getName())) {
        method.setAccessible(true);
        method.invoke(null);
      }
    }
  }
}
