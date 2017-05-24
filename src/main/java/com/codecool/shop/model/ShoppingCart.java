package com.codecool.shop.model;

/**
 * <h1>Class is used to construct ShoppingCarts/h1>
 *
 * @author Adam Kovacs
 * @author Daniel Majoross
 * @author Anna Racz
 * @version 1.0
 * @since 20-05-2017
 */

public class ShoppingCart {

    /**
     * ID of shopping cart declared
     */
    private int id;
    /**
     * Status of shopping cart declared
     */
    private String status;

    /**
     * Constructor of class
     * Sets random ID and default status'NEW'
     */
    public ShoppingCart() {

        this.id = (int) (Math.random() * 10000);
        this.status = "New";

    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Overrides default toString method
     * @return formatted string with attributes of object
     */
    @Override
    public String toString() {
        return String.format("id: %1$d, " + "status: %2$s " + this.id + this.status);
    }
}

