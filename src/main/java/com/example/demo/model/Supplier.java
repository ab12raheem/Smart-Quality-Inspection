package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="supplier",schema = "public")
public class Supplier  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String CompanyName;
    private String address;
    private String postalCode;
    private String phoneNumber;
    private String fax;
    private String paymentMethode;
    private String discountType;


    @OneToOne
    private User user;
    @OneToMany(mappedBy = "supplier")
    @JsonIgnore
    private Set<MaterialSupplier> materialSuppliers=new HashSet<>();

    public Supplier() {

    }

    public Supplier(String companyName, String address, String postalCode, String phoneNumber, String fax,
                    String paymentMethode, String discountType, User user) {
        CompanyName = companyName;
        this.address = address;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.fax = fax;
        this.paymentMethode = paymentMethode;
        this.discountType = discountType;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }


    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
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

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getPaymentMethode() {
        return paymentMethode;
    }

    public void setPaymentMethode(String paymentMethode) {
        this.paymentMethode = paymentMethode;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<MaterialSupplier> getMaterialSuppliers() {
        return materialSuppliers;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "id=" + id +
                ", CompanyName='" + CompanyName + '\'' +
                ", address='" + address + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", fax='" + fax + '\'' +
                ", paymentMethode='" + paymentMethode + '\'' +
                ", discountType='" + discountType + '\'' +
                ", user=" + user +
                '}';
    }
}