package net.dnadas.email_ftp_test.ftp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.integration.ftp.session.DefaultFtpSessionFactory;
import org.springframework.integration.ftp.session.FtpSession;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class FtpService {
  private final DefaultFtpSessionFactory sessionFactory;

  @Autowired
  public FtpService(DefaultFtpSessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public void uploadFile(InputStream inputStream, String remoteFilePath) throws IOException {
    try (FtpSession session = sessionFactory.getSession()) {
      session.write(inputStream, remoteFilePath);
    }
  }


  public Resource downloadFileAsResource(String remotePath) throws IOException {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    try (FtpSession session = sessionFactory.getSession()) {
      session.read(remotePath, byteArrayOutputStream);
    }

    return new ByteArrayResource(byteArrayOutputStream.toByteArray());
  }

}
