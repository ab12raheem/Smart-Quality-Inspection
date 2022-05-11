package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "quality",schema ="public")
public class Quality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    Boolean activate;
    Double area;
    Integer sumColors;
    Integer numDefect;
    Integer numOk;


    public Quality() {
    }

    public Quality(Boolean activate, Double area, Integer sumColors, Integer numDefect, Integer numOk) {
        this.activate = activate;
        this.area = area;
        this.sumColors = sumColors;
        this.numDefect = numDefect;
        this.numOk = numOk;
    }

    public Integer getId() {
        return id;
    }

    public Boolean getActivate() {
        return activate;
    }

    public void setActivate(Boolean activate) {
        this.activate = activate;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Integer getSumColors() {
        return sumColors;
    }

    public void setSumColors(Integer sumColors) {
        this.sumColors = sumColors;
    }

    public Integer getNumDefect() {
        return numDefect;
    }

    public void setNumDefect(Integer numDefect) {
        this.numDefect = numDefect;
    }

    public Integer getNumOk() {
        return numOk;
    }

    public void setNumOk(Integer numOk) {
        this.numOk = numOk;
    }

    @Override
    public String toString() {
        return "Quality{" +
                "id=" + id +
                ", activate=" + activate +
                ", area=" + area +
                ", sumColors=" + sumColors +
                ", numDefect=" + numDefect +
                ", numOk=" + numOk +
                '}';
    }
}
