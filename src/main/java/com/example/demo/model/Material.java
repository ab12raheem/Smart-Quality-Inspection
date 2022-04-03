package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="material",schema = "public")
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer availability;
    private String description;
    private Integer count;
    private String image;
    @JsonIgnore
    @OneToMany(mappedBy = "material")
    private Set<EnrolledMaterials> enrolledMaterials=new HashSet<>();

    @OneToMany(mappedBy = "material")
    @JsonIgnore
    private Set<MaterialSupplier> materialSuppliers =new HashSet<>();



    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "wareHouse_id",referencedColumnName = "id")
    private WareHouse wareHouse;

    public Material() {
    }

    public Material(Integer availability, String description, Integer count, String image) {
        this.availability = availability;
        this.description = description;
        this.count = count;
        this.image = image;
    }
    public WareHouse getWareHouse() {
        return wareHouse;
    }

    public void setWareHouse(WareHouse wareHouse) {
        this.wareHouse = wareHouse;
    }


    public Integer getId() {
        return id;
    }



    public Integer getAvailability() {
        return availability;
    }

    public void setAvailability(Integer availability) {
        this.availability = availability;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<EnrolledMaterials> getEnrolledMaterials() {
        return enrolledMaterials;
    }

    public Set<MaterialSupplier> getMaterialSuppliers() {
        return materialSuppliers;
    }

    @Override
    public String toString() {
        return "Material{" +
                "id=" + id +
                ", availability=" + availability +
                ", description='" + description + '\'' +
                ", count=" + count +
                ", image='" + image + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Material)) return false;
        Material material = (Material) o;
        return Objects.equals(getId(), material.getId()) && Objects.equals(getAvailability(), material.getAvailability()) && Objects.equals(getDescription(), material.getDescription()) && Objects.equals(getCount(), material.getCount()) && Objects.equals(getImage(), material.getImage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAvailability(), getDescription(), getCount(), getImage());
    }
}
