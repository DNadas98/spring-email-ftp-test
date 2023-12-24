package net.dnadas.email_ftp_test.mail.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Properties;

@Configuration
public class MailConfig {

  @Autowired
  private Environment env;

  @Bean
  public JavaMailSender infoMailSender() {
    return createMailSender("spring.info.mail");
  }

  @Bean
  public JavaMailSender noreplyMailSender() {
    return createMailSender("spring.noreply.mail");
  }

  private JavaMailSender createMailSender(String prefix) {
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    mailSender.setHost(env.getProperty(prefix + ".host"));
    String portStr = env.getProperty(prefix + ".port");
    int port = (portStr != null) ? Integer.parseInt(portStr) : 465; // Default port for SSL
    mailSender.setPort(port);
    mailSender.setUsername(env.getProperty(prefix + ".username"));
    mailSender.setPassword(env.getProperty(prefix + ".password"));

    Properties props = new Properties();
    props.put("mail.smtp.auth", true);
    props.put("mail.smtp.socketFactory.port", env.getProperty(prefix + ".properties.mail.smtp.socketFactory.port"));
    props.put("mail.smtp.socketFactory.class", env.getProperty(prefix + ".properties.mail.smtp.socketFactory.class"));
    props.put("mail.smtp.ssl.enable", env.getProperty(prefix + ".properties.mail.smtp.ssl.enable"));
    props.put("mail.smtp.ssl.trust", env.getProperty(prefix + ".properties.mail.smtp.ssl.trust"));

    mailSender.setJavaMailProperties(props);
    return mailSender;
  }
}
