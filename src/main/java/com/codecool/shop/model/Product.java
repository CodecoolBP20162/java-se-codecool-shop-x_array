package com.codecool.shop.model;

import java.util.Currency;

/**
 * <h1>Class is used to construct Products stored in the shopping cart</h1>
 *
 * @author Adam Kovacs
 * @author Daniel Majoross
 * @author Anna Racz
 * @version 1.0
 * @since 20-05-2017
 */

public class Product extends BaseModel {

    /**
     * Default price of the product
     */
    private float defaultPrice;
    /**
     * Default currency of the product
     */
    private Currency defaultCurrency;
    /**
     * Product category of the product
     */
    private ProductCategory productCategory;
    /**
     * Supplier of the product
     */
    private Supplier supplier;

    /**
     * First constructor
     * @param name name of each product instance
     * @param defaultPrice default price of instance
     * @param currencyString currency of instance
     * @param description description of product instance
     * @param productCategory product category of instance
     * @param supplier supplier of product instance
     */
    public Product(String name, float defaultPrice, String currencyString, String description, ProductCategory productCategory, Supplier supplier) {
        super(name, description);
        this.setPrice(defaultPrice, currencyString);
        this.setSupplier(supplier);
        this.setProductCategory(productCategory);

    }

    /**
     * Second constructor which also sets ID
     * @param id ID for each product instance
     * @param name name of each product instances
     * @param defaultPrice default price of instance object
     * @param currencyString currency of instance
     * @param description description of product instance
     * @param productCategory product category of product instance
     * @param supplier supplier of instance
     */
    public Product(int id, String name, float defaultPrice, String currencyString, String description, ProductCategory productCategory, Supplier supplier) {
        super(name);
        this.setId(id);
        this.setDescription(description);
        this.setPrice(defaultPrice, currencyString);
        this.setSupplier(supplier);
        this.setProductCategory(productCategory);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(float defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public Currency getDefaultCurrency() {
        return defaultCurrency;
    }

    public void setDefaultCurrency(Currency defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }

    public String getPrice() {
        return String.valueOf(this.defaultPrice) + " " + this.defaultCurrency.toString();
    }

    public void setPrice(float price, String currency) {
        this.defaultPrice = price;
        this.defaultCurrency = Currency.getInstance(currency);
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
        this.productCategory.addProduct(this);
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
        this.supplier.addProduct(this);
    }

    /**
     * Overwrites default toString method
     * @return formatted string with attributes of object
     */
    @Override
    public String toString() {
        return String.format("id: %1$d, " +
                        "name: %2$s, " +
                        "defaultPrice: %3$f, " +
                        "defaultCurrency: %4$s, " +
                        "productCategory: %5$s, " +
                        "supplier: %6$s",
                this.id,
                this.name,
                this.defaultPrice,
                this.defaultCurrency.toString(),
                this.productCategory.getName(),
                this.supplier.getName());
    }
}
