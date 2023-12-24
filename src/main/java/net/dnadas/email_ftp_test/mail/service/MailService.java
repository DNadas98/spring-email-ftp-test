package net.dnadas.email_ftp_test.mail.service;

import net.dnadas.email_ftp_test.mail.controller.dto.MailRequestDto;
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
  private final JavaMailSender infoMailSender;
  private final String infoEmailAccount;
  private final JavaMailSender noreplyMailSender;
  private final String noreplyEmailAccount;
  private final Logger logger;

  @Autowired
  public MailService(
    JavaMailSender infoMailSender, @Value("${EMAIL_INFO_USERNAME}") String infoEmailAccount,
    JavaMailSender noreplyMailSender,
    @Value("${EMAIL_NOREPLY_USERNAME}") String noreplyEmailAccount) {
    this.infoMailSender = infoMailSender;
    this.noreplyMailSender = noreplyMailSender;
    this.infoEmailAccount = infoEmailAccount;
    this.noreplyEmailAccount = noreplyEmailAccount;
    this.logger = LoggerFactory.getLogger(this.getClass());
  }

  public void sendMailFromUserToInfo(MailRequestDto mailRequest) throws MailException {
    logger.info(mailRequest.toString());
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom(mailRequest.address());
    message.setTo(infoEmailAccount);
    message.setSubject(mailRequest.subject());
    message.setText(mailRequest.content());
    infoMailSender.send(message);
  }

  public void sendMailFromNoreplyToUser(MailRequestDto mailRequest) throws MailException {
    logger.info(mailRequest.toString());
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(mailRequest.address());
    message.setFrom(noreplyEmailAccount);
    message.setSubject(mailRequest.subject());
    message.setText(mailRequest.content());
    noreplyMailSender.send(message);
  }
}
