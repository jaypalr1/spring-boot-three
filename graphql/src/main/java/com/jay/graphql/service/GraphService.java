package com.jay.graphql.service;

import com.jay.generated.types.Book;
import com.jay.generated.types.BookInput;

public interface GraphService {

  Book getData(String input);

  String postData(BookInput book);

  String subscribeData();
}
