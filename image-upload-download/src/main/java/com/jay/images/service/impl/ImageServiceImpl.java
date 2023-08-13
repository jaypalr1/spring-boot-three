package com.jay.images.service.impl;

import com.jay.images.persistence.entity.ImageModel;
import com.jay.images.persistence.repository.ImageModelRepository;
import com.jay.images.service.ImageService;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Jaypal Rathod
 * @implNote Used for Uploading and Downloading an Image
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

  private final ImageModelRepository imageModelRepository;
  private final LobHelper lobCreator;

  @Override
  public String uploadImage(MultipartFile multipartFile) {
    try {
      log.info("Original Image Byte Size - {}", multipartFile.getBytes().length);

      ImageModel imgModel = ImageModel
          .builder()
          .name(multipartFile.getOriginalFilename())
          .type(multipartFile.getContentType())
          .blob(lobCreator.createBlob(multipartFile.getInputStream(), multipartFile.getSize()))
          .build();

      imageModelRepository.save(imgModel);

      return "Image uploaded successfully";
    } catch (Exception e) {
      throw new RuntimeException("");
    }
  }

  @Override
  public void download(String imageName, HttpServletResponse response) {
    var resp = imageModelRepository.findByName(imageName)
        .orElseThrow(() -> new RuntimeException(""));

    try {
      response.addHeader("Content-Disposition", "attachment; filename=" + resp.getName());
      IOUtils.copy(resp.getBlob().getBinaryStream(), response.getOutputStream());

      log.info("Sent file id: {}", resp.getId());
    } catch (Exception e) {
      log.error("Error while reading image");
      throw new RuntimeException("", e);
    }
  }
}
