package com.example.demo.controllers;

import com.example.demo.model.Financial;
import com.example.demo.serveces.FinancialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

@RestController
@RequestMapping("api/v1/financial")
public class FinancialController {
    private final FinancialService financialService;
    @Autowired
    public FinancialController(FinancialService financialService) {
        this.financialService = financialService;
    }

    @GetMapping("getFinancial")
    public Financial getFinancial(){
        return financialService.getFinacial();

    }
}
