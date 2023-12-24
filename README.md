# email-test-app
### Setup & Run
- Copy `env.txt` and rename to `.env`, modify values (all required)
- Run locally from main or with the `docker-compose.yml`

### E-mail sending
- POST `/api/v1/mail`
  - ```
    {
      "from": String, valid e-mail format,
      "subject": String, length: 5-100 characters,
      "content": String, length: 10-500 characters
    }
    ```  
    See `com.dnadas.mailtestapp.mail.controller.dto.MailRequestDto`
