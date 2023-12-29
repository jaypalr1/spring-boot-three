package org.jay.dto;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NestedDto {

  String a;

  Map<String, Object> mixed = new LinkedHashMap<>();

  @JsonAnySetter
  public void setFields(String key, Object value) {
    mixed.put(key, value);
  }
}
