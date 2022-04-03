package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "productsEnrolled",schema = "public")
public class ProductsEnrolled {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer count;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id",referencedColumnName = "id")
    private Product product;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "productEnrolled_id",referencedColumnName = "id")
    private Product productEnrolled;

    public ProductsEnrolled() {
    }

    public ProductsEnrolled(Integer count) {
        this.count = count;
    }

    public Integer getId() {
        return id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProductEnrolled() {
        return productEnrolled;
    }

    public void setProductEnrolled(Product productEnrolled) {
        this.productEnrolled = productEnrolled;
    }

    @Override
    public String toString() {
        return "ProductsEnrolled{" +
                "id=" + id +
                ", count=" + count +
                ", product=" + product +
                ", productEnrolled=" + productEnrolled +
                '}';
    }
}
