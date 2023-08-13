package com.jay.pdf.service.impl;

import com.jay.pdf.persistence.CityRepository;
import com.jay.pdf.persistence.model.City;
import com.jay.pdf.service.CityService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

  private final CityRepository cityRepository;

  @Override
  public List<City> findAll() {
    return cityRepository.findAll();
  }
}
