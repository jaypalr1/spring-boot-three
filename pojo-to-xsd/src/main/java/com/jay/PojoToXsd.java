package com.jay;

import com.jay.dto.Envelope;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.SchemaOutputResolver;
import java.io.File;
import java.io.IOException;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PojoToXsd {

  public static void main(String[] args) {

    try {
      JAXBContext context = JAXBContext.newInstance(Envelope.class);

      SchemaOutputResolver resolver = new SchemaOutputResolver() {
        @Override
        public Result createOutput(String namespaceUri, String suggestedFileName) throws IOException {

          File file = new File(
              "pojo-to-xsd/build/generated/sources/annotationProcessor/java/main/output.xsd");

          StreamResult result = new StreamResult(file);
          result.setSystemId(file.toURI().toURL().toString());

          return result;
        }
      };

      // Use the JAXBContext to generate the XSD
      context.generateSchema(resolver);
    } catch (Exception e) {
      log.error("", e);
    }
  }
}
