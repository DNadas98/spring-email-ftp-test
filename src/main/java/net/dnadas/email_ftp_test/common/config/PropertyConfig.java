package net.dnadas.email_ftp_test.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "file:./.env", encoding = "utf8", ignoreResourceNotFound = true)
public class PropertyConfig {
}
