package com.example.demo.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "assemblyLine", schema = "public")
public class AssemblyLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String stage;
    @OneToOne
    private Department department;

    public AssemblyLine() {
    }

    public AssemblyLine(String stage, Department department) {
        this.stage = stage;
        this.department = department;
    }

    public Integer getId() {
        return id;
    }


    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "AssemblyLine{" +
                "id=" + id +
                ", stage='" + stage + '\'' +
                ", department=" + department.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AssemblyLine)) return false;
        AssemblyLine that = (AssemblyLine) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getStage(), that.getStage()) && Objects.equals(getDepartment(), that.getDepartment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStage(), getDepartment());
    }
}
