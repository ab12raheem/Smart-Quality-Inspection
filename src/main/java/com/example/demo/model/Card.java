package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "card", schema = "public")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    Customer customer;
    @OneToMany(mappedBy = "card")
    @JsonIgnore
    private Set<CardProducts> cardProducts = new HashSet<>();

    public Card() {
    }

    public Card(Customer customer, Set<CardProducts> cardProducts) {
        this.customer = customer;
        this.cardProducts = cardProducts;
    }

    public Integer getId() {
        return id;
    }



    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<CardProducts> getCardProducts() {
        return cardProducts;
    }

    public void setCardProducts(Set<CardProducts> cardProducts) {
        this.cardProducts = cardProducts;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", customer=" + customer +
                ", cardProducts=" + cardProducts +
                '}';
    }
}
