package com.jay.reader.service.impl.helper;

import com.jay.reader.service.Parser;
import com.jay.reader.util.validator.Validator;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.supercsv.io.CsvListReader;
import org.supercsv.prefs.CsvPreference;

@Component
@RequiredArgsConstructor
public class CsvParser implements Parser {

  private static final Logger log = LoggerFactory.getLogger(CsvParser.class);

  @Override
  public Boolean checkFileType(MultipartFile file) {
    return Validator.checkIfCsvFile(file);
  }

  @Override
  public void parse(MultipartFile file) {
    log.info("Started CSV Parsing");

    try (CsvListReader csvReader = new CsvListReader(new InputStreamReader(file.getInputStream()),
        CsvPreference.STANDARD_PREFERENCE)
    ) {
      List<String> header = new ArrayList<>(csvReader.read());
      List<String> rowAsTokens;

      List<String> rows = new ArrayList<>();
      Map<String, String> row;

      long counter = 1L;
      while ((rowAsTokens = csvReader.read()) != null) {
        row = new HashMap<>();

        for (int i = 0; i < header.size(); i++) {
          row.put(header.get(i), rowAsTokens.get(i));
        }

        var updatedString = row.toString()
            .replace("{", "")
            .replace(",", "")
            .replace("}", "")
            .replace(" ", ",")
            .replace("]", "");

        rows.add(",counter=" + counter + " " + updatedString);
        counter++;
      }

      int partitionSize = 5000;
      List<List<String>> subBatteryList = new LinkedList<>();

      for (int i = 0; i < rows.size(); i += partitionSize) {
        subBatteryList.add(rows.subList(i, Math.min(i + partitionSize, rows.size())));
      }

      var size = subBatteryList.size();
    } catch (IOException e) {
      Thread.currentThread().interrupt();
      throw new AssertionError(e.getMessage(), e);
    } catch (Exception e) {
      throw new AssertionError("");
    }
  }
}
