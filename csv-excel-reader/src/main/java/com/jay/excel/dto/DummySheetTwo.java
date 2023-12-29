package com.jay.excel.dto;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelSheet;
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
@ExcelSheet("page_2")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DummySheetTwo {

  @ExcelCellName("first")
  String first;
}
