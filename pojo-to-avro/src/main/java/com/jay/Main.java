package com.jay;

import lombok.extern.slf4j.Slf4j;
import org.apache.avro.Schema;
import org.apache.avro.Schema.Field;
import org.apache.avro.reflect.ReflectData;

@Slf4j
public class Main {

  public static void main(String[] args) {
    // Generate schema from POJO
    Schema schema = ReflectData.get().getSchema(User.class);

    printAvroWithNamespace(schema);
    printAvroWithoutNamespace(schema);
  }


  private static void printAvroWithNamespace(Schema schema) {
    log.info(schema.toString(true));
  }

  // Not working
  private static void printAvroWithoutNamespace(Schema schema) {
    // Remove the namespace
    Schema schemaWithoutNamespace = Schema.createRecord(
        schema.getName(),
        schema.getDoc(),
        null,  // Set namespace to null
        schema.isError()
    );

    for (Field field : schema.getFields()) {
      schemaWithoutNamespace.addProp(field.name(), field.schema());
    }

    log.info(schemaWithoutNamespace.toString(true));
  }
}
