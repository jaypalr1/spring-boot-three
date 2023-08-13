package com.jay.images.service;

import javax.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

  /**
   * @param multipartFile
   * @return
   */
  String uploadImage(MultipartFile multipartFile);

  /**
   * @param imageName
   * @param response
   */
  void download(String imageName, HttpServletResponse response);
}
