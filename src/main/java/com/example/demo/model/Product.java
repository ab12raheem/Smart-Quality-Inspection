package com.example.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "product",schema = "public")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer estimatedTime;
    private String description;
    private Integer quantity;
    private Double  height;
    private  Double width;
    private String photo;
    private Double price;
    private Double percent;
    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private Set<ProductsEnrolled> productsEnrolled=new HashSet<>();
    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private Set<ProductsEnrolled> products=new HashSet<>();




    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private Set<EnrolledMaterials> enrolledMaterials=new HashSet<>();



    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private Set<OrderProducts> orderProducts = new HashSet<>();
    @OneToMany(mappedBy = "product",orphanRemoval = true)
    @JsonIgnore
    private Set<CardProducts> cardProducts = new HashSet<>();



    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "wareHouse_id",referencedColumnName = "id")
    private WareHouse wareHouse;

    public Product() {
    }

    public Product(Integer estimatedTime, String description, Integer quantity, Double height, Double width, String photo,
                   Double percent) {
        this.estimatedTime = estimatedTime;
        this.description = description;
        this.quantity = quantity;
        this.height = height;
        this.width = width;
        this.photo = photo;
        this.percent=percent;
        setPrice();

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


    public Integer getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(Integer estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWidth() {
        return width;
    }

    public Set<EnrolledMaterials> getEnrolledMaterials() {
        return enrolledMaterials;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public String getPhoto() {
        return photo;
    }
    public Set<OrderProducts> getOrderProducts() {
        return orderProducts;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice() {
        Double sum=0.0;
        for(EnrolledMaterials enrolledMaterials1:this.enrolledMaterials){
            sum+=enrolledMaterials1.getCount()*enrolledMaterials1.getMaterial().getPrice();

        }
        for(ProductsEnrolled productsEnrolled1 : this.productsEnrolled){
            sum+=productsEnrolled1.getCount()*productsEnrolled1.getProduct().getPrice();
        }
        sum=sum*this.percent+sum;
        this.price=sum;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public Set<ProductsEnrolled> getProductsEnrolled() {
        return productsEnrolled;
    }

    public Set<ProductsEnrolled> getProducts() {
        return products;
    }

    public Set<CardProducts> getCardProducts() {
        return cardProducts;
    }


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", estimatedTime=" + estimatedTime +
                ", description='" + description + '\'' +
                ", height=" + height +
                ", width=" + width +
                ", photo=" + photo + '\'' +
                ", price="+price+
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equals(getId(), product.getId()) && Objects.equals(getEstimatedTime(), product.getEstimatedTime()) && Objects.equals(getDescription(), product.getDescription()) && Objects.equals(getHeight(), product.getHeight()) && Objects.equals(getWidth(), product.getWidth()) && Objects.equals(getPhoto(), product.getPhoto());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEstimatedTime(), getDescription(), getHeight(), getWidth(), getPhoto());
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
