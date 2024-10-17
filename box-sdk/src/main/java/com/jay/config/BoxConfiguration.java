package com.jay.config;

import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxConfig;
import com.box.sdk.BoxDeveloperEditionAPIConnection;
import com.box.sdk.InMemoryLRUAccessTokenCache;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class BoxConfiguration {

  @Bean
  public BoxAPIConnection box() {
    return BoxDeveloperEditionAPIConnection
        .getAppEnterpriseConnection(boxConfig(), new InMemoryLRUAccessTokenCache(100));
  }

  private BoxConfig boxConfig() {
    try (InputStream inputStream = ClassPathResource.class.getClassLoader()
        .getResourceAsStream("box-config.json")) {

      if (inputStream != null) {
        return BoxConfig.readFrom(new BufferedReader(new InputStreamReader(inputStream)));
      } else {
        throw new RuntimeException("Null inputStream.");
      }
    } catch (IOException e) {
      throw new RuntimeException("Config file not found", e);
    }
  }
}
