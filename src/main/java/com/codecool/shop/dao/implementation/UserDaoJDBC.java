package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.JDBC;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.User;

import java.sql.*;

/**
 * Created by adam_kovacs on 25.05.17.
 */
public class UserDaoJDBC extends JDBC implements UserDao {
    private static UserDaoJDBC instance = null;


    public static UserDaoJDBC getInstance() {

        if (instance == null) {
            instance = new UserDaoJDBC();
        }
        return instance;
    }

    @Override
    public void add(User user) {
        try {
            Connection connection = getConnection();
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO users (user_id, user_name, phone_number, billing_address, shipping_address,email_address) " +
                            "VALUES ( ?, ? , ?, ?, ?, ? );");

            statement.setInt(1, user.getId());
            statement.setString(2, user.getName());
            statement.setString(3, user.getPhoneNumber());
            statement.setString(4, user.getBillingAddress());
            statement.setString(5, user.getShippingAddress());
            statement.setString(6, user.getEmailAddress());
            statement.execute();

            connection.commit();
        } catch (SQLException e) {
            System.out.println("The database died. :(");
        }
    }

    @Override
    public void remove(User user) {
        if (user.getId() < 1) {
            throw new IllegalArgumentException("Id cannot be smaller than 1");
        }

        String query = "DELETE FROM users WHERE user_id = '" + user.getId() + "';";
        executeQuery(query);
    }

    @Override
    public Object find(int id) throws IllegalArgumentException {
        if (id < 1) {
            throw new IllegalArgumentException("id cannot be lower than 1");
        }

        String query = "SELECT * FROM users WHERE user_id ='" + id + "';";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {


            if (resultSet.next()) {

                User user = new User(
                        resultSet.getInt("user_id"),
                        resultSet.getString("user_name"),
                        resultSet.getString("phone_number"),
                        resultSet.getString("billing_address"),
                        resultSet.getString("shipping_address"),
                        resultSet.getString("email_address")
                );

                return user;
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(int id){

    }
}

