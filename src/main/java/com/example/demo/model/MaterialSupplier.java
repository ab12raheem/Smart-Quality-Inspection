package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "materialSupplier",schema = "public")
public class MaterialSupplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer price;
    private Integer estimatedTime;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "supplier_id",referencedColumnName = "id")
    private Supplier supplier;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "material_id",referencedColumnName = "id")
    private Material material;

    public MaterialSupplier() {
    }

    public MaterialSupplier(Integer price, Integer estimatedTime) {
        this.price = price;
        this.estimatedTime = estimatedTime;
    }

    public Integer getId() {
        return id;
    }


    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(Integer estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return "MaterialSupplier{" +
                "id=" + id +
                ", price=" + price +
                ", estimatedTime=" + estimatedTime +
                ", supplier=" + supplier.toString() +
                ", material=" + material.toString()+
                '}';
    }


}
