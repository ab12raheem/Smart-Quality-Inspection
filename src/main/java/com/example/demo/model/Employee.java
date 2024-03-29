package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;
@Entity
@Table(name="employee",schema = "public")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date startingDate;
    private Double salary;
    private Integer role;
    private String image;
    @OneToOne
    private User user;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id",referencedColumnName = "id")
    private Department department;

    public Employee() {
    }

    public Employee(Date startingDate, Double salary
            , Integer role, String image, User user, Department department) {
        this.startingDate = startingDate;
        this.salary = salary;
        this.role = role;
        this.image = image;
        this.user = user;
        this.department=department;
    }

    public Integer getId() {
        return id;
    }



    public Date getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", StartingDate=" + startingDate +
                ", salary=" + salary +
                ", role=" + role +
                ", user=" + user+
                ",department="+department.getName()+
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(getId(), employee.getId()) && Objects.equals(getStartingDate(),
                employee.getStartingDate()) && Objects.equals(getSalary(),
                employee.getSalary())   && Objects.equals(getRole(),
                employee.getRole()) && Objects.equals(getUser(),
                employee.getUser())&&Objects.equals(getDepartment(),
                employee.getDepartment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStartingDate(), getSalary(), getDepartment(),
                getRole(), getUser());
    }
}
