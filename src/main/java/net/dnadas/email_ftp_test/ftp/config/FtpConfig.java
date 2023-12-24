package net.dnadas.email_ftp_test.ftp.config;

import org.apache.commons.net.ftp.FTPSClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.ftp.session.DefaultFtpsSessionFactory;

@Configuration
public class FtpConfig {
  private final String ftpHost;
  private final Integer ftpPort;
  private final String ftpUsername;
  private final String ftpPassword;
  private final Boolean useClientSSLAuth;
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  public FtpConfig(
    @Value("${FTP_HOST}") String ftpHost, @Value("${FTP_PORT}") Integer ftpPort,
    @Value("${FTP_USERNAME}") String ftpUsername, @Value("${FTP_PASSWORD}") String ftpPassword,
    @Value("${FTP_USE_CLIENT_SSL_AUTH}") Boolean useClientSSLAuth) {
    this.ftpHost = ftpHost;
    this.ftpPort = ftpPort;
    this.ftpUsername = ftpUsername;
    this.ftpPassword = ftpPassword;
    this.useClientSSLAuth = useClientSSLAuth;
  }

  @Bean
  public DefaultFtpsSessionFactory ftpsSessionFactory() {
    DefaultFtpsSessionFactory factory = new DefaultFtpsSessionFactory();
    setDefaultFtpSettings(factory);
    if (useClientSSLAuth) {
      factory.setNeedClientAuth(true);
    }
    logger.info(String.format(
      "Configuring FTPS - Host: %s, Port: %d, Username: %s, Client-side SSL authentication: %s",
      ftpHost, ftpPort, ftpUsername, useClientSSLAuth ? "Enabled" : "Disabled"));
    return factory;
  }

  private void setDefaultFtpSettings(DefaultFtpsSessionFactory factory) {
    factory.setHost(ftpHost);
    factory.setPort(ftpPort);
    factory.setControlEncoding("UTF-8");
    factory.setUsername(ftpUsername);
    factory.setPassword(ftpPassword);
    factory.setClientMode(FTPSClient.PASSIVE_LOCAL_DATA_CONNECTION_MODE);
    factory.setConnectTimeout(60000);
  }
}
