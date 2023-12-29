package com.jay.excel.config;

import com.poiji.option.PoijiOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExcelConfig {

  @Bean
  public PoijiOptions poijiOptions() {
    return PoijiOptions.PoijiOptionsBuilder.settings()
        .preferNullOverDefault(true)
        .build();
  }
}
