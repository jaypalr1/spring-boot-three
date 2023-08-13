package com.jay.lombok.nullchecker;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

  @NonNull
  private String name;

  @NonNull
  private String surname;

  private int age;

  public void setNameAndSurname(@NonNull String n, @NonNull String sn) {
    name = n;
    surname = sn;
  }
}
