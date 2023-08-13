package com.jay.images.service.impl;

import java.io.InputStream;
import java.sql.Blob;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LobHelper {

  @Autowired
  private SessionFactory sessionFactory;

  public Blob createBlob(InputStream content, long size) {

    Session session = null;

    try {
      session = sessionFactory.openSession();
      return session.getLobHelper().createBlob(content, size);
    } catch (Exception e) {

      throw new RuntimeException("Error while loading session");
    } finally {
      assert session != null;

      session.close();
    }
  }
}
