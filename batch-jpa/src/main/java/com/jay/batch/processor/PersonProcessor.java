package com.jay.batch.processor;

import com.jay.dto.PersonDto;
import com.jay.entity.PersonEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonProcessor implements ItemProcessor<PersonDto, PersonEntity> {

  /**
   * @param personDto to be processed, never {@code null}.
   * @return PersonEntity
   */
  @Override
  public PersonEntity process(PersonDto personDto) throws Exception {
    log.info("Executing business logic");

    return PersonEntity.builder()
        .name(personDto.getName())
        .lastName(personDto.getLastName())
        .build();
  }
}
