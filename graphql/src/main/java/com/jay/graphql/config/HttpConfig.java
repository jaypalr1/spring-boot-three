package com.jay.graphql.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.client.HttpSyncGraphQlClient;
//import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Slf4j
@Configuration
public class HttpConfig {

  // Synchronous Rest client
//  @Bean
//  public RestClientException restClient() {
//    return RestClient.builder()
//        .build();
//  }

//  @Bean
//  public HttpSyncGraphQlClient httpSyncGraphQlClient(RestClient restClient) {
//    return HttpSyncGraphQlClient.create(restClient)
//        .mutate()
//        .build();
//  }

  // Asynchronous Rest client
//  @Bean
//  public HttpGraphQlClient httpGraphQlClient(WebClient webClient) {
//    return HttpGraphQlClient.create(webClient)
//        .build();
//  }
}
