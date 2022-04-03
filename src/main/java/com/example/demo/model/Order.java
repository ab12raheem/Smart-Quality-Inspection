package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="order",schema = "public")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date orderDate;
    private Date orderDone;
    private Boolean activate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id",referencedColumnName = "id")
    private Customer customer;



    @OneToMany(mappedBy = "order")
    @JsonIgnore
    private Set<OrderProducts> orderProducts = new HashSet<>();



    public Order() {
    }

    public Order(Date orderDate, Date orderDone,Boolean activate) {
        this.orderDate = orderDate;
        this.orderDone = orderDone;
        this.activate=activate;
    }

    public Boolean getActivate() {
        return activate;
    }

    public void setActivate(Boolean activate) {
        this.activate = activate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getOrderDone() {
        return orderDone;
    }

    public void setOrderDone(Date orderDone) {
        this.orderDone = orderDone;
    }
    public Customer getCustomer() {
        return customer;
    }

    public Set<OrderProducts> getOrderProducts() {
        return orderProducts;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setOrderProducts(Set<OrderProducts> orderProducts) {
        this.orderProducts = orderProducts;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", orderDone=" + orderDone +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(getId(), order.getId()) && Objects.equals(getOrderDate(), order.getOrderDate()) && Objects.equals(getOrderDone(), order.getOrderDone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getOrderDate(), getOrderDone());
    }
}
