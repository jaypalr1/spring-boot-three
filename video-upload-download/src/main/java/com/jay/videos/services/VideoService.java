package com.jay.videos.services;

import com.jay.videos.persistence.entity.Video;
import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface VideoService {

  String saveVideo(MultipartFile file, String name) throws IOException;

  Video getVideo(String name);

  List<String> getAllVideoNames();
}
