package com.example.demo.model;

import javax.persistence.*;
import java.sql.Time;

@Table(schema = "public",name = "notification")
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String value;
    private Time time;

    public Notification(String value,Time time) {
        this.value = value;
        this.time=time;
    }


    public Notification() {

    }

    public Integer getId() {
        return id;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
