package com.example.studyhelper.Service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.imageio.ImageIO;
import org.springframework.stereotype.Service;
import com.github.sarxos.webcam.Webcam;

import static com.example.studyhelper.config.Config.FOLDER_PATH;
import static com.example.studyhelper.config.Config.IMAGE_FORMAT;
import static com.example.studyhelper.config.Config.TIME_FORMATTER;

@Service
public class TakePhoto {

    SimpleDateFormat formatter = new SimpleDateFormat(TIME_FORMATTER);

    public String takeScreenShot() throws AWTException, IOException {
        Calendar now = Calendar.getInstance();
        String imageName = formatter.format(now.getTime()) + "." + IMAGE_FORMAT;
        Robot robot = new Robot();
        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
        ImageIO.write(screenFullImage, IMAGE_FORMAT, new File(FOLDER_PATH + imageName));
        return FOLDER_PATH + imageName;
    }


    public String takeWebCamShot() {
        Calendar now = Calendar.getInstance();
        int width = 640, height = 480;
        String imageName = formatter.format(now.getTime()) + "." + IMAGE_FORMAT;
        Webcam webcam = Webcam.getDefault();
        if (webcam != null) {
            webcam.setViewSize(new Dimension(width, height));
            webcam.open();
            try {
                ImageIO.write(webcam.getImage(), IMAGE_FORMAT, new File(FOLDER_PATH + imageName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            webcam.close();
        }
        return FOLDER_PATH + imageName;
    }
}