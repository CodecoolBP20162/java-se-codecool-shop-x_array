package com.codecool.shop.dao;

import com.codecool.shop.model.ProductCategory;

import java.util.List;

/**
 * <h1>Interface to set up the methods implemented later in classes</h1>
 * This interface is extended in ProductCategoryDaoMem and ProductCategoryJDBC.
 * Both provides data to the app.
 * Mem uses the memory, JDBC uses the database
 *
 * @author Adam Kovacs
 * @author Daniel Majoross
 * @author Anna Racz
 * @version 1.0
 * @since 20-05-2017
 */

public interface ProductCategoryDao {

    /**
     * To add a product category object to database
     *
     * @param category object to be added
     */
    void add(ProductCategory category);

    /**
     * To find a product category.
     *
     * @param id ID of product category we are searching for
     * @return ProductCategory found
     * @throws IllegalArgumentException for input errors
     */
    ProductCategory find(int id)throws IllegalArgumentException;

    /**
     * To remove a product category
     *
     * @param id ID of product category to be removed
     * @throws IllegalArgumentException for input errors
     */
    void remove(int id)throws IllegalArgumentException;

    /**
     * To get all ProductCategory objects
     *
     * @return list of all ProductCategory objects stored in database
     */
    List<ProductCategory> getAll();

}
