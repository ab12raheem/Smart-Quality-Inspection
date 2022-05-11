package com.example.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="customer",schema = "public")
public class Customer  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String address;
    private String postalCode;
    private String phoneNumber;
    private String creditCard;
    private String cardID;
    @OneToOne
    private User user;

    private Date registrationDate;
    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonIgnore
    private Card card;
    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private Set<Order> orders = new HashSet<>();
    public Customer() {
    }

    public Customer(String address, String postalCode,
                    String phoneNumber, String creditCard, String cardID, User user) {
        this.address = address;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.creditCard = creditCard;
        this.cardID = cardID;
        this.user = user;

    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Integer getId() {
        return id;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Card getCard() {
        return card;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", creditCard='" + creditCard + '\'' +
                ", cardID='" + cardID + '\'' +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(getId(), customer.getId()) && Objects.equals(getAddress(),
                customer.getAddress()) && Objects.equals(getPostalCode(),
                customer.getPostalCode()) && Objects.equals(getPhoneNumber(),
                customer.getPhoneNumber()) && Objects.equals(getCreditCard(),
                customer.getCreditCard()) && Objects.equals(getCardID(),
                customer.getCardID()) && Objects.equals(getUser(), customer.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAddress(), getPostalCode(),
                getPhoneNumber(), getCreditCard(), getCardID(), getUser());
    }
}