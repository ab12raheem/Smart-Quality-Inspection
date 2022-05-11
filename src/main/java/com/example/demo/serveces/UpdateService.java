package com.example.demo.serveces;

import com.example.demo.model.Quality;
import com.example.demo.model.Update;
import com.example.demo.repositries.UpdateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service

public class UpdateService {
    private final UpdateRepo updateRepo;
    private final QualityService qualityService;
    @Autowired
    public UpdateService(UpdateRepo updateRepo, QualityService qualityService) {
        this.updateRepo = updateRepo;
        this.qualityService = qualityService;
    }
    @Scheduled(initialDelay = 60000L,fixedDelayString = "PT20M")
    public void update(){
        Boolean aBoolean=true;
        Quality quality=qualityService.getByActivate(aBoolean);
        Update update = new Update();
        update.setUpdateDate(Time.valueOf(LocalTime.now()));
        update.setNumOfDefects(quality.getNumDefect());
        update.setNumOfProducts(quality.getNumDefect()+quality.getNumOk());
        updateRepo.save(update);
    }


    public List<Update> getUpdate() {
        return updateRepo.getUpdate();
    }
}
