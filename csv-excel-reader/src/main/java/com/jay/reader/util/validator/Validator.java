package com.jay.reader.util.validator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Validator {

  private static final String CSV_TYPE = "text/csv";
  private static final String EXCEL_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
  private static final String TEXT_TYPE = "text/plain";

  public static boolean checkIfCsvFile(MultipartFile file) {
    return CSV_TYPE.equals(file.getContentType());
  }

  public static boolean checkIfExcelFile(MultipartFile file) {
    return EXCEL_TYPE.equals(file.getContentType());
  }

  public static boolean checkIfTextFile(MultipartFile file) {
    return TEXT_TYPE.equals(file.getContentType());
  }
}
