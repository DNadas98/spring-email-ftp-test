package net.dnadas.email_ftp_test.mail.controller;

import net.dnadas.email_ftp_test.common.controller.dto.MessageResponseDto;
import net.dnadas.email_ftp_test.mail.controller.dto.MailRequestDto;
import net.dnadas.email_ftp_test.mail.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/mail")
public class MailController {
  private final MailService mailService;

  @Autowired
  public MailController(MailService mailService) {
    this.mailService = mailService;
  }

  @PostMapping
  public ResponseEntity<?> sendMailRequest(@RequestBody MailRequestDto mailRequest)
    throws MailException {
    mailRequest.validate();
    mailService.sendMail(mailRequest);
    return ResponseEntity.status(HttpStatus.OK).body(new MessageResponseDto(
      String.format("E-mail sent successfully, Sender: %s, Subject: %s", mailRequest.from(),
        mailRequest.subject())));
  }
}
