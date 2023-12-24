package net.dnadas.email_ftp_test.ftp.controller;

import net.dnadas.email_ftp_test.common.controller.dto.ErrorResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@ControllerAdvice(basePackages = "net.dnadas.email_ftp_test.ftp")
public class FtpExceptionHandler {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @ExceptionHandler(IOException.class)
  public ResponseEntity<?> handleFtpIOException(IOException e) {
    logger.error(e.getMessage());
    String errorMessage = "500 FTP I/O Error";
    if (e.getMessage().contains("Server replied with:")) {
      errorMessage = e.getMessage().split("Server replied with:")[1].trim();
    }
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponseDto(
      String.format("An error has occurred during file transfer: %s", errorMessage)));
  }
}
