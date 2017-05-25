package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;

import java.util.List;

/**
 * <h1>Interface to set up the methods implemented later in classes</h1>
 * This interface is extended in ProductDaoMem and ProductDaoJDBC.
 * Both provides data to the app.
 * Mem uses the memory, JDBC uses the database
 *
 * @author Adam Kovacs
 * @author Daniel Majoross
 * @author Anna Racz
 * @version 1.0
 * @since 20-05-2017
 */

public interface ProductDao {

    /**
     * To add a product object to the database
     *
     * @param product object ot be added
     */
    void add(Product product);

    /**
     * To find a product
     *
     * @param id ID of product we are searching for
     * @return Product found
     * @throws IllegalArgumentException for input errors
     */
    Product find(int id) throws  IllegalArgumentException;

    /**
     * To remove a product
     *
     * @param id ID of product to be removed
     * @throws IllegalArgumentException for input errors
     */
    void remove(int id)throws  IllegalArgumentException;

    /**
     * To get all Product objects
     *
     * @return list of all Product objects
     */
    List<Product> getAll();

    /**
     * To filter products on given supplier
     *
     * @param supplier supplier to filter on
     * @return list of products by given supplier
     */
    List<Product> getBy(Supplier supplier);

    /**
     * To filter products on given product category
     *
     * @param productCategory product category to filter on
     * @return list of products by given product category
     */
    List<Product> getBy(ProductCategory productCategory);

}
