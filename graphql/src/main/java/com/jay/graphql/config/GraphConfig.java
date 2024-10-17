package com.jay.graphql.config;

import graphql.scalars.ExtendedScalars;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

/**
 * @implNote For adding extra scalars data types
 * */
@Slf4j
@Configuration
public class GraphConfig {

  @Bean
  public RuntimeWiringConfigurer runtimeWiringConfigurer() {
    return wiringBuilder -> wiringBuilder
        .scalar(ExtendedScalars.GraphQLBigDecimal)
        .scalar(ExtendedScalars.Date)
        .scalar(ExtendedScalars.DateTime)
        .build();
  }
}
