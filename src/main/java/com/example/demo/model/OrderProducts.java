package com.example.demo.model;


import javax.persistence.*;

@Entity
@Table(name="orderProducts",schema = "public")
public class OrderProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private Integer price;
    private Integer count;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id",referencedColumnName = "id")
    private Order order;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id",referencedColumnName = "id")
    private Product product;

    public OrderProducts() {
    }

    public OrderProducts(Integer count, Order order, Product product) {
        this.count = count;
        this.order = order;
        this.product = product;
        this.price= product.getPrice();
    }

    public Integer getId() {
        return Id;
    }



    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "OrderProducts{" +
                "Id=" + Id +
                ", price=" + price +
                ", count=" + count +
                ", orderId=" + order.getId() +
                ", productId=" + product.getId() +
                '}';
    }

}
