package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="department",schema = "public")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String phone;
    private String fax;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String name;

    @OneToOne(mappedBy = "department", cascade = CascadeType.ALL)
    private WareHouse wareHouse;
    @OneToOne(mappedBy = "department", cascade = CascadeType.ALL)
    private AssemblyLine assemblyLine;
    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private Set<Employee> employeeSet = new HashSet<Employee>();



    public Department() {
    }

    public Department(String phone, String fax, String email, String name) {
        this.phone = phone;
        this.fax = fax;
        this.email = email;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }



    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }
    public Set<Employee> getEmployeeSet() {
        return employeeSet;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", fax='" + fax + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department)) return false;
        Department that = (Department) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getPhone(), that.getPhone()) && Objects.equals(getFax(), that.getFax()) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPhone(), getFax(), getEmail(), getName());
    }
}

