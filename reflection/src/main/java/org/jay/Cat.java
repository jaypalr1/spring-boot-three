package com.jay.reflection;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@SuppressWarnings({"squid:S106"})
public class Cat {

  private final String name;

  private int age;

  public void publicMethod() {
    System.out.println("This is public method");
  }

  public static void publicStaticMethod() {
    System.out.println("This is public STATIC method");
  }

  private void privateMethod() {
    System.out.println("From private method");
  }

  private static void privateStaticMethod() {
    System.out.println("From private STATIC method");
  }
}
