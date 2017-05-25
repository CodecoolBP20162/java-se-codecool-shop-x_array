package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.JDBC;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoJDBC extends JDBC implements ProductCategoryDao {
    private static final Logger logger = LoggerFactory.getLogger(ProductCategoryDaoJDBC.class);
    private static ProductCategoryDaoJDBC instance = null;

    private ProductCategoryDaoJDBC() {
    }

    public static ProductCategoryDaoJDBC getInstance() {
        if (instance == null) {
            instance = new ProductCategoryDaoJDBC();
        }
        return instance;
    }

    public ProductCategory productCategorySetup(ResultSet resultSet) throws SQLException {
        ProductCategory category = new ProductCategory(
                resultSet.getInt("category_id"),
                resultSet.getString("category_name"),
                resultSet.getString("department"),
                resultSet.getString("category_description"));

        return category;
    }


    @Override
    public void add(ProductCategory category) {
        String query = "INSERT INTO categories (category_id,category_name,department,category_description)" +
                "VALUES ('" + category.getId() + "','" + category.getName() + "','" + category.getDepartment() +
                "','" + category.getDescription() + "');";
        executeQuery(query);

    }


    @Override
    public ProductCategory find(int id) throws IllegalArgumentException {
        if (id < 1) {
            logger.warn("Id cannot be smaller than 1");
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


    @Override
    public void remove(int id) throws IllegalArgumentException {
        if (id < 1) {
            logger.warn("Id cannot be smaller than 1");
            throw new IllegalArgumentException("Id cannot be smaller than 1");

        }
        String query = "DELETE FROM categories WHERE category_id = '" + id + "';";
        executeQuery(query);
    }


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
