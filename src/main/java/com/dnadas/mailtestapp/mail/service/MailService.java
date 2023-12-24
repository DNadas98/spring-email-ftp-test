package com.dnadas.mailtestapp.mail.service;

import com.dnadas.mailtestapp.mail.controller.dto.MailRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
  private final JavaMailSender mailSender;
  private final Logger logger;
  private final String ownEmailAccount;

  @Autowired
  public MailService(
    JavaMailSender mailSender, @Value("${EMAIL_USERNAME}") String ownEmailAccount) {
    this.mailSender = mailSender;
    this.ownEmailAccount = ownEmailAccount;
    this.logger = LoggerFactory.getLogger(this.getClass());
  }

  public void sendMail(MailRequestDto emailRequest) throws MailException {
    logger.info(emailRequest.toString());
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom(emailRequest.from());
    message.setTo(ownEmailAccount);
    message.setSubject(emailRequest.subject());
    message.setText(emailRequest.content());
    mailSender.send(message);
  }
}
