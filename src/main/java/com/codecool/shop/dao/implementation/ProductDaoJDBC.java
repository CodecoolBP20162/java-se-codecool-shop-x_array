package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.JDBC;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJDBC extends JDBC implements ProductDao {
    private static final Logger logger = LoggerFactory.getLogger(ProductDaoJDBC.class);
    private static ProductDaoJDBC instance = null;

    private ProductDaoJDBC() {
    }

    public static ProductDaoJDBC getInstance() {

        if (instance == null) {
            instance = new ProductDaoJDBC();
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

    public ProductCategory productCategorySetup(ResultSet resultSet) throws SQLException {
        ProductCategory category = new ProductCategory(
                resultSet.getInt("category_id"),
                resultSet.getString("category_name"),
                resultSet.getString("department"),
                resultSet.getString("category_description"));

        return category;
    }

    public Product productSetup(ResultSet resultSet) throws SQLException {

        Product result = new Product(
                resultSet.getInt("product_id"),
                resultSet.getString("product_name"),
                resultSet.getFloat("default_price"),
                resultSet.getString("currency_string"),
                resultSet.getString("product_description"),
                productCategorySetup(resultSet),
                supplierSetup(resultSet));

        return result;
    }


    @Override
    public void add(Product product) {

        String query = "INSERT INTO products (product_id, product_name, default_price, currency_string, product_description, cat_id, supp_id)"
                + "VALUES ('" + product.getId() + "', '" + product.getName() + "', '" + product.getDefaultPrice() + "', " +
                "'" + product.getDefaultCurrency() + "', '" + product.getDescription() + "', '" + product.getProductCategory() + "'" +
                ", '" + product.getSupplier() + "');";
        executeQuery(query);
    }


    @Override
    public Product find(int id) throws IllegalArgumentException {
        if (id < 1) {
            logger.warn("Id cannot be smaller than 1");
            throw new IllegalArgumentException("id cannot be lower than 1");
        }

        String query = "SELECT * FROM products INNER JOIN suppliers ON products.supp_id=suppliers.supplier_id " +
                "INNER JOIN categories ON products.cat_id=categories.category_id WHERE product_id ='" + id + "';";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {


            if (resultSet.next()) {

                productCategorySetup(resultSet);
                supplierSetup(resultSet);

                return productSetup(resultSet);
            } else {
                logger.warn("Couldn't find item");
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.warn("Couldn't find item");
        return null;
    }


    @Override
    public void remove(int id) throws IllegalArgumentException {
        if (id < 1) {
            logger.warn("Id cannot be smaller than 1");
            throw new IllegalArgumentException("Id cannot be smaller than 1");
        }

        String query = "DELETE FROM products WHERE product_id = '" + id + "';";
        executeQuery(query);
        logger.info("item removed from table");
    }


    @Override
    public List<Product> getAll() {

        Integer numberOfProducts = 0;
        List<Integer> productIDs = new ArrayList<Integer>();
        List<Product> productsFromDB = new ArrayList<Product>();

        String query = "SELECT product_id FROM products;";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                numberOfProducts = resultSet.getInt("product_id");
                productIDs.add(numberOfProducts);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Integer prod : productIDs) {
            productsFromDB.add(find(prod));
        }

        return productsFromDB;
    }


    @Override
    public List<Product> getBy(Supplier supplier) {

        List<Product> productsFromDB = new ArrayList<Product>();
        String query = "SELECT * FROM products INNER JOIN suppliers ON products.supp_id=suppliers.supplier_id" +
                " INNER JOIN categories ON products.cat_id=categories.category_id " +
                "WHERE supp_id='" + supplier.getId() + "';";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                productCategorySetup(resultSet);
                productSetup(resultSet);
                productsFromDB.add(productSetup(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.info("Filtering by supplier from table");
        return productsFromDB;
    }


    @Override
    public List<Product> getBy(ProductCategory productCategory) {

        List<Product> productsFromDB = new ArrayList<Product>();
        String query = "SELECT * FROM products INNER JOIN suppliers ON products.supp_id=suppliers.supplier_id" +
                " INNER JOIN categories ON products.cat_id=categories.category_id " +
                "WHERE cat_id='" + productCategory.getId() + "';";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                supplierSetup(resultSet);
                productSetup(resultSet);
                productsFromDB.add(productSetup(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.info("Filtering by category from table");
        return productsFromDB;
    }
}
