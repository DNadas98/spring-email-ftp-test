# email-test-app
- This project aims to provide simplified examples for secure e-mail sending and FTP file 
  upload-download in Java Spring
- It is intended for learning and testing only, do not use this in production as is

### Setup & Run
- Copy `env.txt` and rename to `.env`, modify values (all required)
- Run locally from main or with the `docker-compose.yml`

### E-mail sending examples

#### From users to a pre-configured "info" e-mail address
- POST `/api/v1/mail/info`
  - ```
    {
      "address": String, valid e-mail format,
      "subject": String, length: 5-100 characters,
      "content": String, length: 10-500 characters
    }
    ```  
  - The address is the "from" address, "to" address is pre-configured
  - Example use case: contact-me form

#### From a no-reply address to users
- POST `/api/v1/mail/noreply`
  - ```
    {
      "address": String, valid e-mail format,
      "subject": String, length: 5-100 characters,
      "content": String, length: 10-500 characters
    }
    ```  
  - The address is the "to" address, "from" is the pre-configured noreply address
  - Example use case: verification codes

### FTP

	@@ -40,4 +52,4 @@
      - full path from the ftp users root, including filename and extension
      - required
  - File sent as a downloadable attachment
  - Example: Click to download
