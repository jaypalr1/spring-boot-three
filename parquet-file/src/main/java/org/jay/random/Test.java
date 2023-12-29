package org.jay.random;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.avro.AvroParquetWriter;
import org.apache.parquet.hadoop.ParquetFileWriter.Mode;
import org.apache.parquet.hadoop.ParquetWriter;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;
import org.jay.random.dto.DataList;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Test {

  public String testParquet(DataList dataList) {

    System.setProperty("hadoop.home.dir", "/");

    ObjectMapper objectMapper = new ObjectMapper();

    try {
      String values = objectMapper.writeValueAsString(dataList);

      Schema schema = parseRequest(values);

      toConvertParquet(dataList, schema);

    } catch (Exception e) {
      log.error("Error.", e);
    }

    return "Done.";
  }

  private Schema parseRequest(String values) {
    TreeMap<String, Object> properties = new TreeMap<>();
    Gson gson = new Gson();

    String schema = "{\"type\" : \"record\", \"name\": \"example\", \"fields\":[";
    properties = gson.fromJson(values, properties.getClass());

    for (String key : properties.keySet()) {

      if (properties.get(key) instanceof Double) {
        schema = schema + "{\"name\" :\"" + key + "\",\"type\":\"" + "double" + "\"},";
      } else if (properties.get(key) instanceof Float) {
        schema = schema + "{\"name\" :\"" + key + "\",\"type\":\"" + "float" + "\"},";
      } else if (properties.get(key) instanceof Long) {
        schema = schema + "{\"name\" :\"" + key + "\",\"type\":\"" + "long" + "\"},";
      } else {
        schema = schema + "{\"name\" :\"" + key + "\",\"type\":\"" + "string" + "\"},";
      }
    }

    schema = schema.substring(0, schema.length() - 1);
    schema = schema + "]}";

    return new Schema.Parser().parse(schema);
  }

  private void toConvertParquet(DataList data, Schema schema) {
//    String tempParquetFilePath = Files.createTempDir().getPath();
    String tempParquetFilePath = "src/main/resources/output";

    List<Map<String, Object>> mapObjects = data.getRecords();

    try (ParquetWriter<GenericData.Record> writer =
        AvroParquetWriter.<GenericData.Record>builder(new Path(tempParquetFilePath))
            .withCompressionCodec(CompressionCodecName.SNAPPY)
            .withSchema(schema)
            .withConf(buildConfig())
            .withWriteMode(Mode.OVERWRITE)
            .build()) {

      GenericData.Record record = new GenericData.Record(schema);

      mapObjects.forEach(d -> {
        try {
          d.forEach((k, v) -> record.put(k, v));

          writer.write(record);
        } catch (IOException e) {
          log.error("IO Exception");
        }
      });
    } catch (Exception e) {
      log.error("Error occurred: ", e);
    }
  }

  private Configuration buildConfig() {
    return new Configuration();
  }
}
