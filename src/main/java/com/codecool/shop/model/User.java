package com.codecool.shop.model;

/**
 * Created by adam_kovacs on 25.05.17.
 */
public class User {
    private int id =1;
    private String name;
    private String phoneNumber;
    private String billingAddress;
    private String shippingAddress;
    private String emailAddress;

    public User(String name, String phoneNumber, String billingAddress, String shippingAddress, String emailAddress){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
        this.emailAddress = emailAddress;
    }

    public User(int id, String name, String phoneNumber, String billingAddress, String shippingAddress, String emailAddress){
        this(name,phoneNumber,billingAddress, shippingAddress, emailAddress);
        this.id = id;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getId() {
        return id;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}
