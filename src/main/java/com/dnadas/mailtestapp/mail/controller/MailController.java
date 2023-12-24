package com.dnadas.mailtestapp.mail.controller;

import com.dnadas.mailtestapp.common.controller.dto.MessageResponseDto;
import com.dnadas.mailtestapp.mail.controller.dto.MailRequestDto;
import com.dnadas.mailtestapp.mail.service.MailService;
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
  public ResponseEntity<?> sendMailRequest(@RequestBody MailRequestDto emailRequest)
    throws MailException {
    emailRequest.validate();
    mailService.sendMail(emailRequest);
    return ResponseEntity.status(HttpStatus.OK).body(new MessageResponseDto(
      String.format("Email sent successfully, Sender: %s, Subject: %s", emailRequest.from(),
        emailRequest.subject())));
  }
}
