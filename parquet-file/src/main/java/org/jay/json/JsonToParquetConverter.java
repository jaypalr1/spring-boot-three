package org.jay.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.avro.AvroParquetWriter;
import org.apache.parquet.avro.AvroWriteSupport;
import org.apache.parquet.hadoop.ParquetFileWriter;
import org.apache.parquet.hadoop.ParquetWriter;
import org.apache.parquet.hadoop.api.WriteSupport;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;
import org.apache.parquet.schema.MessageType;
import org.apache.parquet.schema.MessageTypeParser;

@Slf4j
public class JsonToParquetConverter {

  public static void main(String[] args) throws IOException {
    // Define the Avro schema
    Schema avroSchema = new Schema
        .Parser()
        .parse(
            "{\"type\": \"record\", \"name\": \"example\", \"fields\": [ {\"name\":\"name\", \"type\":\"string\"}, {\"name\":\"age\", \"type\":\"int\"} ] }");

    // Define the Parquet schema based on the Avro schema
    MessageType parquetSchema = MessageTypeParser.parseMessageType(avroSchema.toString());

    // Create a Parquet file writer
    Path outputPath = new Path(new File("src/main/resources/output/json-out.parquet").toURI());
    log.info("{}", outputPath);

    WriteSupport<GenericRecord> writeSupport = new AvroWriteSupport<>(parquetSchema, avroSchema);

    ParquetWriter<GenericRecord> parquetWriter = AvroParquetWriter.<GenericRecord>builder(outputPath)
        .withSchema(avroSchema)
        .withConf(new Configuration())
        .withCompressionCodec(CompressionCodecName.SNAPPY) // Optional
        .withWriteMode(ParquetFileWriter.Mode.OVERWRITE)
//        .withWriteSupport(writeSupport)
        .build();

    // JSON input data (example)
    String jsonInput = "{\"name\": \"John\", \"age\": 30}";

    // Parse the JSON data
    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode jsonNode = objectMapper.readTree(jsonInput);

    // Create a GenericRecord from the JSON data
    GenericRecord record = new GenericData.Record(avroSchema);
    record.put("name", jsonNode.get("name").asText());
    record.put("age", jsonNode.get("age").asInt());

    // Write the record to the Parquet file
    parquetWriter.write(record);

    // Close the Parquet writer
    parquetWriter.close();
  }
}
