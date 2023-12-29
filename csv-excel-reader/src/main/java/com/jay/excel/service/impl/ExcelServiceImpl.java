package com.jay.excel.service.impl;

import com.jay.excel.dto.DummySheetOne;
import com.jay.excel.dto.DummySheetThree;
import com.jay.excel.dto.DummySheetTwo;
import com.jay.excel.service.ExcelService;
import com.poiji.bind.Poiji;
import com.poiji.exception.PoijiExcelType;
import com.poiji.option.PoijiOptions;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExcelServiceImpl implements ExcelService {

  private final PoijiOptions poijiOptions;

  @Override
  public String readExcelWithPoiji() {
    File inputFile = new File("csv-excel-reader/src/main/resources/excel/test_excel_data.xlsx");
//      InputStream inputStream = new FileInputStream(inputFile);

    List<DummySheetOne> dummySheetOneList =
        Poiji.fromExcel(inputFile, DummySheetOne.class, poijiOptions);

    dummySheetOneList.forEach(System.out::println);

    List<DummySheetTwo> dummySheetTwoList =
        Poiji.fromExcel(inputFile, DummySheetTwo.class, poijiOptions);

    dummySheetTwoList.forEach(System.out::println);

    List<DummySheetThree> dummySheetThreeList =
        Poiji.fromExcel(inputFile, DummySheetThree.class, poijiOptions);

    dummySheetThreeList.forEach(System.out::println);

    return "Success";
  }

  @Override
  public String readExcelWithPoijiInputStream() {
    try {
      File inputFile = new File("csv-excel-reader/src/main/resources/excel/test_excel_data.xlsx");
      InputStream inputStream = new FileInputStream(inputFile);

      List<DummySheetOne> dummySheetOneList =
          Poiji.fromExcel(inputStream, PoijiExcelType.XLSX, DummySheetOne.class, poijiOptions);

      dummySheetOneList.forEach(System.out::println);
      System.out.println("========================");

      List<DummySheetTwo> dummySheetTwoList =
          Poiji.fromExcel(inputStream, PoijiExcelType.XLSX, DummySheetTwo.class, poijiOptions);

      dummySheetTwoList.forEach(System.out::println);
      System.out.println("========================");

      List<DummySheetThree> dummySheetThreeList =
          Poiji.fromExcel(inputStream, PoijiExcelType.XLSX, DummySheetThree.class, poijiOptions);

      dummySheetThreeList.forEach(System.out::println);

      return "Success";
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public String readExcelWithApachePoi() {
    File inputFile = new File("csv-excel-reader/src/main/resources/excel/test_excel_data.xlsx");

    try {
      FileInputStream file = new FileInputStream(inputFile);
      Workbook workbook = new XSSFWorkbook(file);

      int numberOfSheets = workbook.getNumberOfSheets();

      for (int i = 0; i < numberOfSheets; i++) {
        Sheet currentSheet = workbook.getSheetAt(i);

        //every sheet has rows, iterate over them
        Iterator<Row> rowIterator = currentSheet.iterator();
        while (rowIterator.hasNext()) {

        }
      }

      return "Success";
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
