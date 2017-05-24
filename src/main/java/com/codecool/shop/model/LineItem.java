package com.codecool.shop.model;

import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;

import java.util.Currency;

/**
 * <h1>Class is used to construct LineItems stored in the shopping cart</h1>
 * Each LineItem object is constructed of a Product object and a quantity attribute
 *
 * @author Adam Kovacs
 * @author Daniel Majoross
 * @author Anna Racz
 * @version 1.0
 * @since 20-05-2017
 */

public class LineItem {

    /**
     * ID attribute of LineItem instance
     */
    private int id;
    /**
     * The Product object for the LineItem instance
     */
    private Product product;
    /**
     * Quantity attribute for LineItem instance
     */
    private int quantity;
    /**
     * Subtotal price per LineItem, quantity*amount
     */
    private float subtotalPrice;
    static private int sumOfAll;

    /**
     * Constructor for LineItem
     * @param product Product object to be included in line item
     * @param quantity attribute of product in line item
     */
    public LineItem(Product product, int quantity) {
        this.product = product;
        this.id = product.getId();
        this.quantity = quantity;
        this.subtotalPrice = product.getDefaultPrice() * quantity;
        this.sumOfAll += (int) product.getDefaultPrice() * quantity;

    }

    /**
     * Alternative constructor for LineItem
     * @param id ID of the object
     * @param product Product object to be included in line item
     * @param quantity attribute of product in line item
     */
    public LineItem(int id, Product product, int quantity) {
        this.id = id;
        this.product = product;
        this.id = product.getId();
        this.quantity = quantity;
        this.sumOfAll += (int) product.getDefaultPrice() * quantity;

    }
    /**
     * Changes amount of line item by given num parameter
     * If amount in cart would be 0 line item is removed from cart
     * @param num amount in cart is increased by num
     */
    public void changeAmount(int num) {
        quantity += num;
        sumOfAll += product.getDefaultPrice() * num;
        if (quantity < 1) {
            ShoppingCartDaoMem.getInstance().remove(this);
        }
    }

    public float getSubtotalPrice() {
        return this.subtotalPrice;
    }

    public float getProductDefaultPrice() {
        return this.product.getDefaultPrice();
    }

    public String getName() {
        return this.product.getName();
    }

    public int getId() {
        return id;
    }

    public int getProductId() {
        return this.product.getId();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUnitPrice() {
        return (int) this.product.getDefaultPrice();
    }

    public int getTotalPrice() {
        return getUnitPrice() * this.quantity;
    }

    public Product getProduct() {
        return this.product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSumOfAll() { return sumOfAll; }

    public Currency getDefaultCurrency() {
        return this.product.getDefaultCurrency();
    }
}

