package com.example.demo.controllers;

import com.example.demo.model.Financial;
import com.example.demo.serveces.FinancialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/financial")
@CrossOrigin(origins = "http://localhost:3000")
public class FinancialController {
    private final FinancialService financialService;
    @Autowired
    public FinancialController(FinancialService financialService) {
        this.financialService = financialService;
    }
    @PreAuthorize("hasRole('Admin')")
    @GetMapping("getFinancial")
    public Financial getFinancial(){
        return financialService.getFinacial();

    }
    @GetMapping("getReport")
    public List<Financial> getLastTenRecords(){
        return financialService.getLastTenRecords();
    }
}
