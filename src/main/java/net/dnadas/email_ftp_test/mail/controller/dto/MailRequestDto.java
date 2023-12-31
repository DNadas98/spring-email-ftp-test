package net.dnadas.email_ftp_test.mail.controller.dto;

import net.dnadas.email_ftp_test.mail.exception.MailContentFormatException;
import net.dnadas.email_ftp_test.mail.exception.MailAddressFormatException;
import net.dnadas.email_ftp_test.mail.exception.MailSubjectFormatException;

import java.util.regex.Pattern;

public record MailRequestDto(String address, String subject, String content) {

  private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
  private static final int SUBJECT_MIN_LENGTH = 5;
  private static final int SUBJECT_MAX_LENGTH = 100;
  private static final int CONTENT_MIN_LENGTH = 10;
  private static final int CONTENT_MAX_LENGTH = 500;

  public void validate()
    throws MailAddressFormatException, MailSubjectFormatException, MailContentFormatException {
    if (address == null || address.trim().isEmpty() || !EMAIL_PATTERN.matcher(address).matches()) {
      throw new MailAddressFormatException();
    }
    if (subject == null || subject.trim().length() < SUBJECT_MIN_LENGTH ||
      subject.trim().length() > SUBJECT_MAX_LENGTH) {
      throw new MailSubjectFormatException();
    }
    if (content == null || content.trim().length() < CONTENT_MIN_LENGTH ||
      content.trim().length() > CONTENT_MAX_LENGTH) {
      throw new MailContentFormatException();
    }
  }

  @Override
  public String toString() {
    return String.format("E-mail address: %s, Subject: %s, Content: %s", address, subject, content);
  }
}
