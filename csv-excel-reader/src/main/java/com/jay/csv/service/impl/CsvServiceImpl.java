package com.jay.csv.service.impl;

import com.jay.csv.dto.DummyDto;
import com.jay.csv.service.CsvService;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CsvServiceImpl implements CsvService {

  @Override
  public String readCsvWithOpenCsv() {
    try {
      FileInputStream inputStream = new FileInputStream(
          "csv-excel-reader/src/main/resources/csv/test-csv-data.csv");

      CsvToBean<DummyDto> dummyDtoReader = setupCsvBeanBuilder(inputStream);

      List<DummyDto> dummyDtoList = dummyDtoReader.parse();

      dummyDtoList.forEach(System.out::println);
    } catch (Exception e) {
      log.error("Error", e);
    }

    return "Success";
  }

  @Override
  public String readCsvWithApacheCsv() {
//    String[] columns = {"first", "middle", "last"};
    String[] columns = {"first", "last"};

    CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
        .setHeader(columns)
        .setSkipHeaderRecord(true)
        .build();

    try {
      FileInputStream inputStream = new FileInputStream(
          "csv-excel-reader/src/main/resources/csv/test-csv-data.csv");

      Reader reader = new InputStreamReader(inputStream);

      Iterable<CSVRecord> csvRecords = csvFormat.parse(reader);

      for (CSVRecord eachRecord : csvRecords) {
        String name = eachRecord.get("first");

        log.info("Name: {}", name);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return "Success";
  }

  private CsvToBean<DummyDto> setupCsvBeanBuilder(InputStream inputStream) {
    Reader reader = new InputStreamReader(inputStream);

    CsvToBeanBuilder<DummyDto> dummyDtoCsvToBeanBuilder = new CsvToBeanBuilder<>(reader);
    dummyDtoCsvToBeanBuilder.withType(DummyDto.class);
    dummyDtoCsvToBeanBuilder.withSeparator(',');
    dummyDtoCsvToBeanBuilder.withIgnoreEmptyLine(true);
    dummyDtoCsvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);

    return dummyDtoCsvToBeanBuilder.build();
  }
}
