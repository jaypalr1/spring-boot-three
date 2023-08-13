package com.jay.images.persistence.repository;

import com.jay.images.persistence.entity.ImageModel;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageModelRepository extends JpaRepository<ImageModel, Long> {

  Optional<ImageModel> findByName(String name);
}
