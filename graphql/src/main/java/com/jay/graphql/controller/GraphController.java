package com.jay.graphql.controller;

import com.jay.generated.types.Book;
import com.jay.generated.types.BookInput;
import com.jay.graphql.service.GraphService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.client.GraphQlClient;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class GraphController {

  private final GraphService graphService;
//  private final RestClient restClient;

  @QueryMapping
  public Book getBookData(@Argument String id) {
    log.info("getBookData: {}", id);

    return graphService.getData(id);
  }

  @MutationMapping
  public String postBookData(@Argument BookInput bookInput) {
    return graphService.postData(bookInput);
  }

  @SubscriptionMapping
  public String subscribeData() {
    return graphService.subscribeData();
  }

//  @GetMapping
//  public ResponseEntity<String> get() {
//    restClient.mutate()
//        .build();
//
//    GraphQlClient graphQlClient = HttpGraphQlClient.builder()
//        .build();
//
//    return new ResponseEntity<>("Data", HttpStatus.OK);
//  }
}
