package com.codecool.shop.model;

import java.util.ArrayList;

/**
 * <h1>Class is used to construct ProductCategories</h1>
 *
 * @author Adam Kovacs
 * @author Daniel Majoross
 * @author Anna Racz
 * @version 1.0
 * @since 20-05-2017
 */

public class ProductCategory extends BaseModel {
    /**
     * Department of product category
     */
    private String department;
    /**
     * List declared for products in product category
     */
    private ArrayList<Product> products;

    /**
     * First constructor for class
     * @param name name of ProductCategory instance
     * @param department department of category
     * @param description description of product category object
     */
    public ProductCategory(String name, String department, String description) {
        super(name);
        this.department = department;
        this.products = new ArrayList<>();
    }

    /**
     * Second constructor, also sets ID
     * @param id ID for category instance
     * @param name name of ProductCategory instance
     * @param department department of category
     * @param description description of product category object
     */
    public ProductCategory(int id, String name, String department, String description) {
        super(name);
        this.id = id;
        this.department = department;
        this.description = description;
        this.products = new ArrayList<>();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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
        return String.format(
                "id: %1$d, " +
                        "name: %2$s, " +
                        "department: %3$s, " +
                        "description: %4$s",
                this.id,
                this.name,
                this.department,
                this.description);
    }
}