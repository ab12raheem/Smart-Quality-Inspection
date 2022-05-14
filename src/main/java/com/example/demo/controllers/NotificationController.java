package com.example.demo.controllers;

import com.example.demo.model.Notification;
import com.example.demo.repositries.NotificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/notify")
@CrossOrigin(origins = "http://localhost:3000")

public class NotificationController {
    private final NotificationRepo notificationRepo;
    @Autowired
    public NotificationController(NotificationRepo notificationRepo) {
        this.notificationRepo = notificationRepo;
    }
    @GetMapping("getLastNotify")
    public List<Notification> getLast(){
        System.out.println("..................................fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff................................................................................");
        for(Notification notification:notificationRepo.getLast()){
            System.out.println(notification.toString());
        }
        System.out.println(notificationRepo.getLast().size());
        if(notificationRepo.getLast().isEmpty()){
            throw new IllegalStateException("ggggg");
        }
        return notificationRepo.getLast();
    }
}
