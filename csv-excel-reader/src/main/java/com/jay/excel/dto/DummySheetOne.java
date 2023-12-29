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
@ExcelSheet("page_1")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DummySheetOne {


  @ExcelCellName("first")
  String first;

  String middle;

  @ExcelCellName("last")
  String last;
}
