package com.example.studyhelper.config;

import com.example.studyhelper.Service.CheckingMail;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private final CheckingMail email;

    public ScheduledTasks(CheckingMail email) {
        this.email = email;
    }
}