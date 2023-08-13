package com.jay.pdf.persistence;

import com.jay.pdf.persistence.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {

}
