# email-test-app
- This project aims to provide simplified examples for e-mail sending and FTP file 
  upload-download in Java Spring
- It is intended for learning and testing only, do NOT use this in production

### Setup & Run
- Copy `env.txt` and rename to `.env`, modify values (all required)
- Run locally from main or with the `docker-compose.yml`

### E-mail

#### E-mail sending to the configured e-mail address
- POST `/api/v1/mail`
  - ```
    {
      "from": String, valid e-mail format,
      "subject": String, length: 5-100 characters,
      "content": String, length: 10-500 characters
    }
    ```  
    See `dto.controller.mail.net.dnadas.email_ftp_test.MailRequestDto`
  - Example use case: "Contact Me" form

### FTP

#### Upload
- POST `/api/v1/ftp/upload`
  - Parameters:
    - `file`: MultipartFile
      - required
    - `remotePath`: String
      - full path from the ftp users root, including the filename and extension
      - required
  - Example: Form with `file` as the file input field and `remotePath` as a text input field.

#### Download
- GET `/api/v1/ftp/download?remotePath=`
  - Parameter:
    - `remotePath`: String
      - full path from the ftp users root, including filename and extension
      - required
  - File sent as a downloadable attachment
  - Example: Click to download