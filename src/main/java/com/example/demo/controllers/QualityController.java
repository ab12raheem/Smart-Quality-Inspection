package com.example.demo.controllers;

import com.example.demo.model.Quality;
import com.example.demo.serveces.QualityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/quality")
public class QualityController {
    private final QualityService qualityService;
    @Autowired
    public QualityController(QualityService qualityService) {
        this.qualityService = qualityService;
    }
    @GetMapping
    public List<Quality> getAll(){
        return qualityService.getAll();
    }
    @GetMapping("byId/{id}")
    public Quality getById(@PathVariable Integer id){
        return qualityService.getById(id);
    }
    @GetMapping("getPercentageOfDefect")
    public Double getPercentageOfDefect(){
        return qualityService.getPercentageOfDefect();
    }
    @GetMapping("getPercentageOfOk")
    public Double getPercentageOfOk(){
        return qualityService.getPercentageOfOk();
    }

    @GetMapping("getPercentageOfDefectById/{id}")
    public Double getPercentageOfDefectById(@PathVariable Integer id){
        return qualityService.getPercentageOfDefectById(id);
    }
    @GetMapping("getPercentageOfOkById/{id}")
    public Double getPercentageOfOkById(@PathVariable Integer id){
        return qualityService.getPercentageOfOkById(id);
    }
    @PutMapping("activate/{id}")
    public void activate(@PathVariable Integer id){
        qualityService.activate(id);
    }

    



}
