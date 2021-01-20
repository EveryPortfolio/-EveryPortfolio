package com.everyportfolio.user.configuration;

import lombok.AllArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.Properties;

@AllArgsConstructor
@Configuration
@PropertySource(value = {"file:///c:/naver_mail.properties", "file:/etc/properties/naver_mail.properties"}, ignoreResourceNotFound = true)
public class WebConfiguration {
    private static final String MAIL_DEBUG = "mail.debug";
    private static final String MAIL_SMTP_STARTTLS_REQUIRED = "mail.smtp.starttls.required";
    private static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
    private static final String MAIL_SMTP_STARTTLS_ENABLE = "mail.smtp.starttls.enable";

    private Environment environment;

    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(environment.getProperty("spring.email.host"));
        mailSender.setProtocol(environment.getProperty("spring.email.protocol"));
        mailSender.setPort(Integer.parseInt(environment.getProperty("spring.email.port")));
        mailSender.setUsername(environment.getProperty("spring.email.username"));
        mailSender.setPassword(environment.getProperty("spring.email.password"));
        mailSender.setDefaultEncoding("UTF-8");
        Properties properties = mailSender.getJavaMailProperties();
        properties.put(MAIL_SMTP_STARTTLS_REQUIRED, true);
        properties.put(MAIL_SMTP_STARTTLS_ENABLE, true);
        properties.put(MAIL_SMTP_AUTH, true);
        properties.put(MAIL_DEBUG, true);
        mailSender.setJavaMailProperties(properties);
        return mailSender;
    }
}
