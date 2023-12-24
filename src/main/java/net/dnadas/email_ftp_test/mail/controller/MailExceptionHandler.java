package net.dnadas.email_ftp_test.mail.controller;

import net.dnadas.email_ftp_test.common.controller.dto.ErrorResponseDto;
import net.dnadas.email_ftp_test.mail.exception.MailContentFormatException;
import net.dnadas.email_ftp_test.mail.exception.MailSenderFormatException;
import net.dnadas.email_ftp_test.mail.exception.MailSubjectFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailException;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailSendException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = "net.dnadas.email_ftp_test.mail")
public class MailExceptionHandler {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @ExceptionHandler(MailAuthenticationException.class)
  public ResponseEntity<?> authenticationExceptionHandler(MailAuthenticationException e) {
    logger.error(e.getMessage());
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
      new ErrorResponseDto("E-mail authentication failed"));
  }

  @ExceptionHandler(MailParseException.class)
  public ResponseEntity<?> parseExceptionHandler(MailParseException e) {
    logger.error(e.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
      new ErrorResponseDto("The format of the received message is invalid"));
  }

  @ExceptionHandler(MailSendException.class)
  public ResponseEntity<?> sendExceptionHandler(MailSendException e) {
    logger.error(e.getMessage());
    return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(
      new ErrorResponseDto("Failed to send e-mail, please try again later"));
  }

  @ExceptionHandler(MailException.class)
  public ResponseEntity<?> mailGeneralExceptionHandler(MailException e) {
    logger.error(e.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
      new ErrorResponseDto("Failed to send e-mail"));
  }

  @ExceptionHandler(MailSenderFormatException.class)
  public ResponseEntity<?> senderFormatExceptionHandler() {
    String error = "Invalid sender e-mail address format";
    logger.error(error);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDto(error));
  }

  @ExceptionHandler(MailSubjectFormatException.class)
  public ResponseEntity<?> subjectFormatExceptionHandler() {
    String error = "Invalid e-mail subject format";
    logger.error(error);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDto(error));
  }

  @ExceptionHandler(MailContentFormatException.class)
  public ResponseEntity<?> contentFormatExceptionHandler() {
    String error = "Invalid e-mail content format";
    logger.error(error);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDto(error));
  }
}
