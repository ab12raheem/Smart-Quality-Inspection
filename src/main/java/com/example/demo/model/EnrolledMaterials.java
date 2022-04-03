package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "enrolledMaterials",schema = "public")
public class EnrolledMaterials {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer count;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "material_id",referencedColumnName = "id")
    private Material material;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id",referencedColumnName = "id")
    private Product product;

    public EnrolledMaterials() {
    }

    public EnrolledMaterials(Integer count) {
        this.count = count;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "EnrolledMaterials{" +
                "id=" + id +
                ", count=" + count +
                ", material=" + material +
                ", product=" + product +
                '}';
    }
}
