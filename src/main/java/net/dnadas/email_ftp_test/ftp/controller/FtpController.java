package net.dnadas.email_ftp_test.ftp.controller;

import net.dnadas.email_ftp_test.ftp.service.FtpService;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/v1/ftp")
public class FtpController {
  private final FtpService ftpService;

  @Autowired
  public FtpController(FtpService ftpService) {
    this.ftpService = ftpService;
  }

  @PostMapping("/upload")
  public ResponseEntity<?> uploadFile(
    @RequestParam("file") MultipartFile file,
    @RequestParam("remotePath") String remotePath) throws IOException {
    ftpService.uploadFile(file.getInputStream(), remotePath);
    return ResponseEntity.ok("File uploaded successfully to " + remotePath);
  }

  @GetMapping("/download")
  public ResponseEntity<Resource> downloadFile(@RequestParam("remotePath") String remotePath) throws IOException {
    Resource fileResource = ftpService.downloadFileAsResource(remotePath);

    String filename = Paths.get(remotePath).getFileName().toString();
    return ResponseEntity.ok()
      .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
      .body(fileResource);
  }
}
