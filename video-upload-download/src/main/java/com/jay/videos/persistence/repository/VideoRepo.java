package com.jay.videos.persistence.repository;

import com.jay.videos.persistence.entity.Video;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepo extends JpaRepository<Video, Long> {

  Optional<Video> findByName(String name);

  boolean existsByName(String name);

  @Query(nativeQuery = true, value = "SELECT name FROM video")
  List<String> getAllEntryNames();
}
