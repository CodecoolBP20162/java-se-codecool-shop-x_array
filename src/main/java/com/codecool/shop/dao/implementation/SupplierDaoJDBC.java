package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.JDBC;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Class to extend JDBC and implement SupplierDao</h1>
 * This implementation uses the database to store data.
 *
 * @author Adam Kovacs
 * @author Daniel Majoross
 * @author Anna Racz
 * @version 1.0
 * @since 20-05-2017
 */

public class SupplierDaoJDBC extends JDBC implements SupplierDao {
    private static SupplierDaoJDBC instance = null;

    /**
     * ShoppingCartDaoJDBC empty constructor
     */
    private SupplierDaoJDBC() {
    }

    /**
     * To get instance of SupplierDaoJDBC if none exists
     *
     * @return instance of SupplierDaoJDBC
     */
    public static SupplierDaoJDBC getInstance() {
        if (instance == null) {
            instance = new SupplierDaoJDBC();
        }
        return instance;
    }

    /**
     * To set up supplier from database
     *
     * @param resultSet result set of SQL query from database
     * @return supplier Supplier object
     * @throws SQLException for invalid input
     */
    public Supplier supplierSetup(ResultSet resultSet) throws SQLException {
        Supplier supplier = new Supplier(
                resultSet.getInt("supplier_id"),
                resultSet.getString("supplier_name"),
                resultSet.getString("supplier_description"));

        return supplier;
    }

    /**
     * @see SupplierDao#add(Supplier)
     *
     */
    @Override
    public void add(Supplier supplier) {

        String query = "INSERT INTO suppliers (supplier_id,supplier_name,supplier_description)" +
                "VALUES ('" + supplier.getId() + "','" + supplier.getName() + "','"
                + supplier.getDescription() + "');";
        executeQuery(query);
    }

    /**
     * @see SupplierDao#find(int)
     *
     */
    @Override
    public Supplier find(int id) throws IllegalArgumentException {
        if (id < 1) {
            throw new IllegalArgumentException("Id cannot be smaller than 1");
        }

        String query = "SELECT * FROM suppliers WHERE supplier_id = '" + id + "' ;";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            if (resultSet.next()) {
                return supplierSetup(resultSet);
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * @see SupplierDao#remove(int)
     *
     */
    @Override
    public void remove(int id) throws IllegalArgumentException {
        if (id < 1) {
            throw new IllegalArgumentException("Id cannot be smaller than 1");
        }

        String query = "DELETE FROM suppliers WHERE supplier_id = '" + id + "';";
        executeQuery(query);
    }

    /**
     * @see SupplierDao#getAll()
     *
     */
    @Override
    public List<Supplier> getAll() {


        List<Supplier> suppliersFromDB = new ArrayList<Supplier>();
        String query = "SELECT * FROM suppliers;";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {

                suppliersFromDB.add(supplierSetup(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suppliersFromDB;
    }
}
