package com.jay.processor;

import com.jay.dto.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PersonItemProcessor implements ItemProcessor<Person, Person> {

  /**
   * @return Person
   */
  @Override
  public Person process(Person person) {

    String firstName = person.getFirstName().toUpperCase();
    String lastName = person.getLastName().toUpperCase();

    Person transformedPerson = new Person(firstName, lastName);

    log.info("Converting (" + person + ") into (" + transformedPerson + ")");

    return transformedPerson;
  }
}
