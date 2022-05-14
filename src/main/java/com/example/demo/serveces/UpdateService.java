package com.example.demo.serveces;

import com.example.demo.model.Notification;
import com.example.demo.model.Quality;
import com.example.demo.model.Update;
import com.example.demo.repositries.NotificationRepo;
import com.example.demo.repositries.UpdateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

@Service

public class UpdateService {
    private final UpdateRepo updateRepo;
    private final QualityService qualityService;
    private final NotificationRepo notificationRepo;
    @Autowired
    public UpdateService(UpdateRepo updateRepo, QualityService qualityService, NotificationRepo notificationRepo) {
        this.updateRepo = updateRepo;
        this.qualityService = qualityService;
        this.notificationRepo = notificationRepo;
    }
    @Scheduled(initialDelay = 6000L,fixedDelayString = "PT20M")
    public void update(){
        Boolean aBoolean=true;
        Quality quality=qualityService.getByActivate(aBoolean);
        Update update = new Update();
        update.setUpdateDate(Time.valueOf(LocalTime.now()));
        update.setNumOfDefects(quality.getNumDefect());
        update.setNumOfProducts(quality.getNumDefect()+quality.getNumOk());
        Double defect= quality.getNumDefect().doubleValue()/(quality.getNumDefect()+quality.getNumOk());
        System.out.println(defect);
        if(defect>0.2){
            Notification notification=new Notification();
            notification.setValue("out of control");
            notification.setTime(Time.valueOf(LocalTime.now()));
            notificationRepo.save(notification);
        }
        updateRepo.save(update);

    }


    public List<Update> getUpdate() {
        return updateRepo.getUpdate();
    }
}
