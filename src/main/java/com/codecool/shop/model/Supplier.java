package com.codecool.shop.model;

import java.util.ArrayList;

/**
 * <h1>Class is used to construct Suppliers/h1>
 *
 * @author Adam Kovacs
 * @author Daniel Majoross
 * @author Anna Racz
 * @version 1.0
 * @since 20-05-2017
 */

public class Supplier extends BaseModel {
    /**
     * List for Products by supplier declared
     */
    private ArrayList<Product> products;

    /**
     * First constructor
     * @param name name for each instance
     * @param description description for Supplier instance
     */
    public Supplier(String name, String description) {
        super(name, description);
        this.products = new ArrayList<>();
    }

    /**
     * Second constructor, also sets ID
     * @param id ID for Supplier object
     * @param name name for each instance
     * @param description description for Supplier instance
     */
    public Supplier(int id, String name, String description) {
        super(name);
        this.id = id;
        this.description = description;
        this.products = new ArrayList<>();
    }


    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList getProducts() {
        return this.products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    /**
     * Overwrites default toString method
     * @return formatted string with attributes of object
     */
    @Override
    public String toString() {
        return String.format("id: %1$d, " +
                        "name: %2$s, " +
                        "description: %3$s",
                this.id,
                this.name,
                this.description
        );
    }
}