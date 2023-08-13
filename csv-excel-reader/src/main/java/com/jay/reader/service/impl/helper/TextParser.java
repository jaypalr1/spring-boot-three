package com.jay.reader.service.impl.helper;

import com.jay.reader.service.Parser;
import com.jay.reader.util.validator.Validator;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Component
@RequiredArgsConstructor
public class TextParser implements Parser {

  @Override
  public Boolean checkFileType(MultipartFile file) {
    return Validator.checkIfTextFile(file);
  }

  @Override
  public void parse(MultipartFile file) {
    log.info("Started Text File Parsing");

    try (BufferedReader reader = new BufferedReader(
        new InputStreamReader(file.getInputStream()))) {
      String line;

      final List<String[]> allRecords = new ArrayList<>();

      while ((line = reader.readLine()) != null) {
        final StringTokenizer tokenizer
            = new StringTokenizer(line, System.getProperty("line.separator"));

        while (tokenizer.hasMoreTokens()) {
          allRecords.add(tokenizer.nextToken().split("\t"));
        }
      }

      var maccorPostgresMap = new HashMap<String, String>();
      for (int i = 0; i < 13; i++) {
        var recordFromList = allRecords.get(i);
        maccorPostgresMap.put(recordFromList[0].replace(":", ""), recordFromList[1]);
      }

      allRecords.remove(14);

//      var maccorInfluxRecords = new ArrayList<InfluxRecords>();

      if (file.getName().endsWith("_time.txt")) {
        for (int i = 14; i < allRecords.size(); i++) {
          var singleRecord = allRecords.get(i);
//          var timeSeriesRecord = MaccorTimeSeries.builder()
//              .rec(singleRecord[0])
//              .time(singleRecord[1])
//              .voltage(singleRecord[2])
//              .current(singleRecord[3])
//              .build();

//          maccorInfluxRecords.add(timeSeriesRecord);
        }
      } else {
        for (int i = 14; i < allRecords.size(); i++) {
          var singleRecord = allRecords.get(i);
//          var cycliRecord = MaccorCyclic.builder()
//              .number(singleRecord[0])
//              .cycleNo(singleRecord[1])
//              .chargeCapacity(singleRecord[2])
//              .dischargeCapacity(singleRecord[3])
//              .build();

//          maccorInfluxRecords.add(cycliRecord);
        }
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("IO Error while reading file", e);
    } catch (Exception e) {
      throw new IllegalArgumentException("Exception occurred while reading file", e);
    }
  }
}
