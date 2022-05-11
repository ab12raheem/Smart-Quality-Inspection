package com.example.demo.controllers;

import com.example.demo.model.Update;
import com.example.demo.serveces.UpdateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/update")
public class UpdateController {
    private final UpdateService updateService;

    public UpdateController(UpdateService updateService) {
        this.updateService = updateService;
    }

    @GetMapping("getUpdate")
    public List<Update> getUpdate(){
        return updateService.getUpdate();
    }
}
