package org.jay.dto;

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
public class DynamicRequestDto {

  Map<String, Object> fields = new LinkedHashMap<>();

  NestedDto nestedDto;

//  @JsonAnySetter
//  public void setFields(String key, Object value) {
//    fields.put(key, value);
//  }
}
