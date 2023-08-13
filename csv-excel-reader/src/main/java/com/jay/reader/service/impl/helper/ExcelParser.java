package com.jay.reader.service.impl.helper;

import com.jay.reader.service.Parser;
import com.jay.reader.util.validator.Validator;
import java.io.IOException;
import java.util.Iterator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExcelParser implements Parser {

  @Override
  public Boolean checkFileType(MultipartFile file) {
    return Validator.checkIfExcelFile(file);
  }

  @Override
  public void parse(MultipartFile file) {
    log.info("Started Excel File Parsing");

    try {
      Workbook workbook = new XSSFWorkbook(file.getInputStream());
      var sheets = workbook.getNumberOfSheets();

      for (int i = 0; i < sheets; i++) {
        Sheet sheet = workbook.getSheetAt(i);

        Iterator<Row> rows = sheet.iterator();
//        List<BiologicCyclic> memoryPointList = new ArrayList<>();

        int rowNumber = 0;
        while (rows.hasNext()) {
          Row currentRow = rows.next();

          if (rowNumber == 0) {
            rowNumber++;
            continue;
          }

          Iterator<Cell> cellsInRow = currentRow.iterator();
//          BiologicCyclic biologicCyclic = new BiologicCyclic();

          int cellIndex = 0;

          while (cellsInRow.hasNext()) {
            Cell currentCell = cellsInRow.next();

            switch (cellIndex) {
//              case 0 -> biologicCyclic.setLoop(currentCell.getNumericCellValue());
//              case 1 -> biologicCyclic.setCycle(currentCell.getNumericCellValue());
//              case 2 -> biologicCyclic.setExpt(currentCell.getStringCellValue());
//              case 3 -> biologicCyclic.setQanByMaH(currentCell.getNumericCellValue());
//              case 4 -> biologicCyclic.setQcaByMaH(currentCell.getNumericCellValue());
//              case 5 -> biologicCyclic.setIanByMaMin(currentCell.getNumericCellValue());
//              case 6 -> biologicCyclic.setIanByMaMax(currentCell.getNumericCellValue());
//              case 7 -> biologicCyclic.setIcaByMaMin(currentCell.getNumericCellValue());
//              case 8 -> biologicCyclic.setIcaByMaMax(currentCell.getNumericCellValue());
//              case 9 -> biologicCyclic.setEanByVend(currentCell.getNumericCellValue());
//              case 10 -> biologicCyclic.setEcaByVend(currentCell.getNumericCellValue());
//              case 11 -> biologicCyclic.setEceByVmin(currentCell.getNumericCellValue());
//              case 12 -> biologicCyclic.setEceByVmax(currentCell.getNumericCellValue());
//              case 13 -> biologicCyclic.setAnInOneByVmin(currentCell.getNumericCellValue());
//              case 14 -> biologicCyclic.setAnInOneByVmax(currentCell.getNumericCellValue());
//              case 15 -> biologicCyclic.setAnInTwoByVmin(currentCell.getNumericCellValue());
//              case 16 -> biologicCyclic.setAnInTwoByVmax(currentCell.getNumericCellValue());
//              case 17 -> biologicCyclic.setAnInThreeByVmin(currentCell.getNumericCellValue());
//              case 18 -> biologicCyclic.setAnInThreeByVmax(currentCell.getNumericCellValue());
              default -> throw new IllegalStateException("Unexpected value: " + cellIndex);
            }

//            cellIndex++;
          }

//          memoryPointList.add(biologicCyclic);
        }
      }

      workbook.close();
    } catch (IOException e) {
      throw new AssertionError(e.getMessage(), e);
    } catch (Exception e) {
      throw new AssertionError("AppConstants.INFLUX_ERROR_GENERIC_MSG", e);
    }
  }
}
