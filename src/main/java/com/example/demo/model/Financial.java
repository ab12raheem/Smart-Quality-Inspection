package com.example.demo.model;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.sql.Date;
import java.time.Month;
import java.time.Year;

@Entity
@Table(name = "financial",schema = "public")
public class Financial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private Integer customerCount;
    private Integer customerToLastMonth;
    private Double income;
    private Double outcome;
    private Double profitsOfTheMonth;
    @Column(unique = true)
    private Date month;
    private Double profitPercent;
    private Double profitsToLastMonth;


    public Financial() {
    }

    public Financial(Integer customerCount, Double income, Double outcome,
                     Double profitsOfTheMonth, Date month, Double profitPercent, Double profitsToLastMonth) {
        this.customerCount = customerCount;
        this.income = income;
        this.outcome = outcome;
        this.profitsOfTheMonth = profitsOfTheMonth;
        this.month = month;
        this.profitPercent = profitPercent;
        this.profitsToLastMonth = profitsToLastMonth;
    }

    public Integer getCustomerToLastMonth() {
        return customerToLastMonth;
    }

    public void setCustomerToLastMonth(Integer customerToLastMonth) {
        this.customerToLastMonth = customerToLastMonth;
    }

    public Integer getId() {
        return Id;
    }

    public Integer getCustomerCount() {
        return customerCount;
    }

    public void setCustomerCount(Integer customerCount) {
        this.customerCount = customerCount;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Double getOutcome() {
        return outcome;
    }

    public void setOutcome(Double outcome) {
        this.outcome = outcome;
    }

    public Double getProfitsOfTheMonth() {
        return profitsOfTheMonth;
    }

    public void setProfitsOfTheMonth(Double profitsOfTheMonth) {
        this.profitsOfTheMonth = profitsOfTheMonth;
    }

    public Date getMonth() {
        return month;
    }

    public void setMonth(Date month) {
        this.month = month;
    }

    public Double getProfitPercent() {
        return profitPercent;
    }

    public void setProfitPercent(Double profitPercent) {
        this.profitPercent = profitPercent;
    }

    public Double getProfitsToLastMonth() {
        return profitsToLastMonth;
    }

    public void setProfitsToLastMonth(Double profitsToLastMonth) {
        this.profitsToLastMonth = profitsToLastMonth;
    }

    @Override
    public String toString() {
        return "Financial{" +
                "Id=" + Id +
                ", customerCount=" + customerCount +
                ", income=" + income +
                ", outcome=" + outcome +
                ", profitsOfTheMonth=" + profitsOfTheMonth +
                ", month=" + month +
                ", profitPercent=" + profitPercent +
                ", profitsToLastMonth=" + profitsToLastMonth +
                '}';
    }
}
