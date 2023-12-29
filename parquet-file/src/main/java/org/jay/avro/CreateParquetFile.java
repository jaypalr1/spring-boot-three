package org.jay.avro;

import java.io.File;
import java.io.IOException;
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

public class CreateParquetFile {

  public static void main(String[] args) throws IOException {
    // Define the Avro schema
    Schema avroSchema = new Schema
        .Parser()
        .parse(
        "{ \"type\": \"record\", \"name\": \"example\", \"fields\": [ {\"name\":\"name\", \"type\":\"string\"}, {\"name\":\"age\", \"type\":\"int\"} ] }");

    // Define the Parquet schema based on the Avro schema
    MessageType parquetSchema = MessageTypeParser.parseMessageType(avroSchema.toString());

    // Create a Parquet file writer
    Path outputPath = new Path(new File("src/main/resources/output/avro-out.parquet").toURI());

    WriteSupport<GenericRecord> writeSupport = new AvroWriteSupport<>(parquetSchema, avroSchema);
    ParquetWriter<GenericRecord> parquetWriter =
        AvroParquetWriter.<GenericRecord>builder(outputPath)
            .withSchema(avroSchema)
            .withConf(new Configuration())
            .withCompressionCodec(CompressionCodecName.SNAPPY) // Optional
            .withWriteMode(ParquetFileWriter.Mode.OVERWRITE)
//            .withWriteSupport(writeSupport)
            .build();

    // Create a sample record and write it to the Parquet file
    GenericRecord record = new GenericData.Record(avroSchema);
    record.put("name", "John");
    record.put("age", 30);
    parquetWriter.write(record);

    // Close the Parquet writer
    parquetWriter.close();
  }
}
