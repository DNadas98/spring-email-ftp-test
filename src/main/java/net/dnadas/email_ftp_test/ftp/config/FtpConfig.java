package net.dnadas.email_ftp_test.ftp.config;

import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.ftp.session.DefaultFtpSessionFactory;

@Configuration
public class FtpConfig {
  private final String ftpHost;
  private final Integer ftpPort;
  private final String ftpUsername;
  private final String ftpPassword;
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  public FtpConfig(
    @Value("${FTP_HOST}") String ftpHost, @Value("${FTP_PORT}") Integer ftpPort,
    @Value("${FTP_USERNAME}") String ftpUsername, @Value("${FTP_PASSWORD}") String ftpPassword) {
    this.ftpHost = ftpHost;
    this.ftpPort = ftpPort;
    this.ftpUsername = ftpUsername;
    this.ftpPassword = ftpPassword;
  }

  @Bean
  public DefaultFtpSessionFactory sftpSessionFactory() {
    DefaultFtpSessionFactory factory = new DefaultFtpSessionFactory();
    factory.setHost(ftpHost);
    factory.setPort(ftpPort);
    factory.setControlEncoding("UTF-8");
    factory.setUsername(ftpUsername);
    factory.setPassword(ftpPassword);
    factory.setClientMode(FTPClient.PASSIVE_LOCAL_DATA_CONNECTION_MODE);
    factory.setConnectTimeout(60000);
    logger.info(
      String.format("Configuring FTP - host: %s, port: %d, username: %s", ftpHost, ftpPort,
        ftpUsername));
    return factory;
  }
}
