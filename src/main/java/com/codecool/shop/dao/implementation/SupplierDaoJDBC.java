package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.JDBC;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoJDBC extends JDBC implements SupplierDao {
    private static final Logger logger = LoggerFactory.getLogger(SupplierDaoJDBC.class);
    private static SupplierDaoJDBC instance = null;

    private SupplierDaoJDBC() {
    }

    public static SupplierDaoJDBC getInstance() {
        if (instance == null) {
            instance = new SupplierDaoJDBC();
        }
        return instance;
    }

    public Supplier supplierSetup(ResultSet resultSet) throws SQLException {
        Supplier supplier = new Supplier(
                resultSet.getInt("supplier_id"),
                resultSet.getString("supplier_name"),
                resultSet.getString("supplier_description"));

        return supplier;
    }


    @Override
    public void add(Supplier supplier) {

        String query = "INSERT INTO suppliers (supplier_id,supplier_name,supplier_description)" +
                "VALUES ('" + supplier.getId() + "','" + supplier.getName() + "','"
                + supplier.getDescription() + "');";
        executeQuery(query);
    }


    @Override
    public Supplier find(int id) throws IllegalArgumentException {
        if (id < 1) {
            logger.warn("Id cannot be smaller than 1");
            throw new IllegalArgumentException("Id cannot be smaller than 1");
        }

        String query = "SELECT * FROM suppliers WHERE supplier_id = '" + id + "' ;";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            if (resultSet.next()) {
                return supplierSetup(resultSet);
            } else {
                logger.warn("Couldn't find supplier");
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void remove(int id) throws IllegalArgumentException {
        if (id < 1) {
            logger.warn("Id cannot be smaller than 1");
            throw new IllegalArgumentException("Id cannot be smaller than 1");
        }

        String query = "DELETE FROM suppliers WHERE supplier_id = '" + id + "';";
        executeQuery(query);
    }


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
