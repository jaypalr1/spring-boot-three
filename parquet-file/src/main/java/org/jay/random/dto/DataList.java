package org.jay.random.dto;

import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
public class DataList {

  private List<Map<String, Object>> records;
}
