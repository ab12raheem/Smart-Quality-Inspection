package com.example.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="WareHouse",schema = "public")
public class WareHouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer capacity;
    @OneToOne
    private Department department;



    @OneToMany(mappedBy = "wareHouse")
    @JsonIgnore
    private Set<Material> materials=new HashSet<>();

    @OneToMany(mappedBy = "wareHouse")
    @JsonIgnore
    private Set<Product> products=new HashSet<>();

    public Set<Product> getProducts() {
        return products;
    }

    public WareHouse() {
    }

    public WareHouse(Integer id, Integer capacity, Department department) {
        this.id = id;
        this.capacity = capacity;
        this.department = department;
    }
    public Set<Material> getMaterials() {
        return materials;
    }

    public Integer getId() {
        return id;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "WareHouse{" +
                "id=" + id +
                ", capacity=" + capacity +
                ", department=" + department.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WareHouse)) return false;
        WareHouse wareHouse = (WareHouse) o;
        return Objects.equals(getId(), wareHouse.getId()) && Objects.equals(getCapacity(), wareHouse.getCapacity()) && Objects.equals(getDepartment(), wareHouse.getDepartment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCapacity(), getDepartment());
    }
}
