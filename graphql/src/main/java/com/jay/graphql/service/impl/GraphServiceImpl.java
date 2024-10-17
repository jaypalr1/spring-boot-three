package com.jay.graphql.service.impl;

import com.jay.generated.types.Author;
import com.jay.generated.types.Book;
import com.jay.generated.types.BookInput;
import com.jay.graphql.service.GraphService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GraphServiceImpl implements GraphService {

  @Override
  public Book getData(String id) {
    return Book.newBuilder()
        .id(id)
        .name("Test Book")
        .pageCount(1)
        .author(Author.newBuilder()
            .id(id)
            .firstName("Test")
            .lastName("Book")
            .build())
        .build();
  }

  @Override
  public String postData(BookInput book) {
    return "Book saved successfully";
  }

  @Override
  public String subscribeData() {
    return "";
  }
}
