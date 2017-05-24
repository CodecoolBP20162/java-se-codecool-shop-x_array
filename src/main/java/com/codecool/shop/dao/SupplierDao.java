package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;

import java.util.List;

/**
 * <h1>Interface to set up the methods implemented later in classes</h1>
 * This interface is extended in SupplierDaoMem and SupplierDaoJDBC.
 * Both provides data to the app.
 * Mem uses the memory, JDBC uses the database
 *
 * @author Adam Kovacs
 * @author Daniel Majoross
 * @author Anna Racz
 * @version 1.0
 * @since 20-05-2017
 *
 */

public interface SupplierDao {

    /**
     * To add Supplier object to database
     * @param supplier object to be added
     */
    void add(Supplier supplier);

    /**
     * To find a supplier
     * @param id ID of supplier we are searching for
     * @return Supplier found
     * @throws IllegalArgumentException for input errors
     */
    Supplier find(int id) throws IllegalArgumentException;

    /**
     * To remove a supplier
     * @param id ID of supplier to be removed
     * @throws IllegalArgumentException for input errors
     */
    void remove(int id)throws IllegalArgumentException;

    /**
     * To get all Supplier objects
     * @return list of all Supplier objects
     */
    List<Supplier> getAll();
}
