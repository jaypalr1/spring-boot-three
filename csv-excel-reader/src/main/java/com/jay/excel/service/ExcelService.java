package com.jay.excel.service;

public interface ExcelService {

  String readExcelWithPoiji();

  String readExcelWithPoijiInputStream();

  String readExcelWithApachePoi();
}
