package com.example.demo.model;


import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "update",schema = "public")
public class Update {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    Time updateDate;
    Integer numOfProducts;
    Integer numOfDefects;


    public Update() {
    }

    public Update(Time updateDate, Integer numOfProducts, Integer numOfDefects) {
        this.updateDate = updateDate;
        this.numOfProducts = numOfProducts;
        this.numOfDefects = numOfDefects;
    }

    public Integer getId() {
        return id;
    }



    public Time getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Time updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getNumOfProducts() {
        return numOfProducts;
    }

    public void setNumOfProducts(Integer numOfProducts) {
        this.numOfProducts = numOfProducts;
    }

    public Integer getNumOfDefects() {
        return numOfDefects;
    }

    public void setNumOfDefects(Integer numOfDefects) {
        this.numOfDefects = numOfDefects;
    }

    @Override
    public String toString() {
        return "Update{" +
                "id=" + id +
                ", updateDate=" + updateDate +
                ", numOfProducts=" + numOfProducts +
                ", numOfDefects=" + numOfDefects +
                '}';
    }
}
