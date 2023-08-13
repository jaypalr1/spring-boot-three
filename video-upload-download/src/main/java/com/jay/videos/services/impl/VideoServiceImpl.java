package com.jay.videos.services.impl;

import com.jay.videos.exceptions.VideoAlreadyExistsException;
import com.jay.videos.exceptions.VideoNotFoundException;
import com.jay.videos.persistence.entity.Video;
import com.jay.videos.persistence.repository.VideoRepo;
import com.jay.videos.services.VideoService;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {

  private final VideoRepo repo;

  @Override
  public String saveVideo(MultipartFile file, String name) throws IOException {
    if (repo.existsByName(name)) {
      throw new VideoAlreadyExistsException();
    }

    Video newVid = new Video(name, file.getBytes());
    repo.save(newVid);

    return "Video saved successfully.";
  }

  @Override
  public Video getVideo(String name) {
    return repo.findByName(name).orElseThrow(VideoNotFoundException::new);
  }

  @Override
  public List<String> getAllVideoNames() {
    return repo.getAllEntryNames();
  }
}
