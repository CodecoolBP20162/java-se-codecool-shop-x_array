package com.codecool.shop.dao;

import com.codecool.shop.model.LineItem;

import java.util.List;

/**
 * <h1>Interface to set up the methods implemented later in classes</h1>
 * This interface is extended in ShoppingCartDaoMem and ShoppingCartDaoJDBC.
 * Both provides data to the app.
 * Mem uses the memory, JDBC uses the database
 *
 * @author Adam Kovacs
 * @author Daniel Majoross
 * @author Anna Racz
 * @version 1.0
 * @since 20-05-2017
 */

public interface ShoppingCartDao {

    /**
     * To add a product via a LineItem object to database
     *
     * @param item object to be added to cart, built from a Product object and a quantity
     */
    void add(LineItem item);

    /**
     * To remove a LineItem from database
     *
     * @param item object to be removed from cart, built from a Product object and a quantity
     */
    void remove(LineItem item);

    /**
     * To find LineItem by its ID in database
     *
     * @param id ID of LineItem object
     * @return LineItem found
     */
    LineItem find(int id);

    /**
     * To get all LineItems in shopping cart from database
     *
     * @return list of all LineItem objects in shopping cart
     */
    List<LineItem> getAll();

    /**
     * To get total price of all products in shopping cart
     *
     * @return total price of LineItems as a string
     */
    String getTotal();

    /**
     * To increase or decrease LineItem quantity
     *
     * @param item LineItem to be incr/decr in quantity
     * @param num  the amount to incr/decr quantity by
     */
    void changeAmount(LineItem item, int num);
}

