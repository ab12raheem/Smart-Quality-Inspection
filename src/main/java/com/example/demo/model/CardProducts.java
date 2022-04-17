package com.example.demo.model;

import javax.persistence.*;

@Table(name="cardProducts" , schema = "public")
@Entity
public class CardProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private Integer price;
    private Integer count;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "card_id",referencedColumnName = "id")
    private Card card;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id",referencedColumnName = "id")
    private Product product;

    public CardProducts() {
    }

    public CardProducts(Integer price, Integer count, Card card, Product product) {
        this.price = product.getPrice();
        this.count = count;
        this.card = card;
        this.product = product;
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

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "CardProducts{" +
                "Id=" + Id +
                ", price=" + price +
                ", count=" + count +
                ", card=" + card +
                ", product=" + product +
                '}';
    }
}
