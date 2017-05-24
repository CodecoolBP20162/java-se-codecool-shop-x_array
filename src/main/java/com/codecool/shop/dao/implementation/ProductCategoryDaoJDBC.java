package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.JDBC;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Class to extend JDBC and implement ProductCategoryDao</h1>
 * This implementation uses the database to store data.
 *
 * @author Adam Kovacs
 * @author Daniel Majoross
 * @author Anna Racz
 * @version 1.0
 * @since 20-05-2017
 *
 */

public class ProductCategoryDaoJDBC extends JDBC implements ProductCategoryDao {
    private static ProductCategoryDaoJDBC instance = null;

    /**
     * ProductCategoryDaoJDBC empty constructor
     */
    private ProductCategoryDaoJDBC() {
    }

    /**
     * To get instance of ProductCategoryDaoJDBC if none exists
     * @return instance of ProductCategoryDaoJDBC
     */
    public static ProductCategoryDaoJDBC getInstance() {
        if (instance == null) {
            instance = new ProductCategoryDaoJDBC();
        }
        return instance;
    }

    /**
     * To set up product category from database
     *
     * @param resultSet result set of SQL query from database
     * @return category ProductCategory object
     * @throws SQLException for invalid input
     */
    public ProductCategory productCategorySetup(ResultSet resultSet) throws SQLException {
        ProductCategory category = new ProductCategory(
                resultSet.getInt("category_id"),
                resultSet.getString("category_name"),
                resultSet.getString("department"),
                resultSet.getString("category_description"));

        return category;
    }

    /**
     * @see ProductCategoryDao#add(ProductCategory)
     *
     */
    @Override
    public void add(ProductCategory category) {
        String query = "INSERT INTO categories (category_id,category_name,department,category_description)" +
                "VALUES ('" + category.getId() + "','" + category.getName() + "','" + category.getDepartment() +
                "','" + category.getDescription() + "');";
        executeQuery(query);

    }

    /**
     * @see ProductCategoryDao#find(int)
     *
     */
    @Override
    public ProductCategory find(int id) throws IllegalArgumentException {
        if (id < 1) {
            throw new IllegalArgumentException("Id cannot be smaller than 1");
        }
        String query = "SELECT * FROM categories WHERE category_id = '" + id + "' ;";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {


            if (resultSet.next()) {

                return productCategorySetup(resultSet);

            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * @see ProductCategoryDao#remove(int)
     *
     */
    @Override
    public void remove(int id) throws IllegalArgumentException {
        if (id < 1) {
            throw new IllegalArgumentException("Id cannot be smaller than 1");
        }
        String query = "DELETE FROM categories WHERE category_id = '" + id + "';";
        executeQuery(query);
    }


    /**
     * @see ProductCategoryDao#getAll()
     *
     */
    @Override
    public List<ProductCategory> getAll() {
        List<ProductCategory> productCategoriesFromDB = new ArrayList<ProductCategory>();
        String query = "SELECT * FROM categories;";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {

                productCategoriesFromDB.add(productCategorySetup(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productCategoriesFromDB;
    }
}
