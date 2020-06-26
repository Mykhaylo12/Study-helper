package com.example.studyhelper.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import static com.example.studyhelper.config.Config.IMAGE_FORMAT;
import static com.example.studyhelper.config.Config.TIME_FORMATTER;

@Service
public class SendEmail {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(Address[] sentTo, String absolutePath) {
        SimpleDateFormat formatter = new SimpleDateFormat(TIME_FORMATTER);
        Calendar now = Calendar.getInstance();
        String time = formatter.format(now.getTime());
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(sentTo[0].toString());
            helper.setSubject("Photo time -> " + time);
            helper.setText("Thank you for choosing our service!");
            FileSystemResource file = new FileSystemResource(new File(absolutePath));
            helper.addAttachment(time + "." + IMAGE_FORMAT, file);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        mailSender.send(message);
    }
}
