package com.uniqueGames.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

//    @Autowired
//    private Environment environment;
    @Value("${email-address}")
    private String EMAIL_ADDRESS;
    @Value("${email-password}")
    private String EMAIL_PASSWORD;

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
//        mailSender.setUsername(environment.getProperty("EMAIL_ADDRESS"));
//        mailSender.setPassword(environment.getProperty("EMAIL_PASSWORD"));
        mailSender.setUsername(EMAIL_ADDRESS);
        mailSender.setPassword(EMAIL_PASSWORD);

        Properties mailProperties = new Properties();
        mailProperties.put("mail.transport.protocol", "smtp");
        mailProperties.put("mail.smtp.auth", "true");
        mailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        mailProperties.put("mail.smtp.starttls.enable", "true");
        mailProperties.put("mail.debug", "true");
        mailProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        mailProperties.put("mail.smtp.ssl.protocols", "TLSv1.2");

        mailSender.setJavaMailProperties(mailProperties);

        return mailSender;
    }
}
