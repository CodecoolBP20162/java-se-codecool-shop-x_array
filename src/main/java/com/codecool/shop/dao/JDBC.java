package com.codecool.shop.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * <h1>Abstract class for database related methods, inherited by other classes</h1>
 * This class includes the most frequently used jdbc-related methods and included in other classes
 * by inheritance.
 *
 * @author Adam Kovacs
 * @author Daniel Majoross
 * @author Anna Racz
 * @version 1.0
 * @since 20-05-2017
 *
 */

public abstract class JDBC {

    /**
     * Establishes connection with the database
     * @return connection with properties from the read() method
     * @throws SQLException to handle database connection issues
     */
    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://" + read(1) + "/" + read(2) + "",
                read(3),
                read(4));
    }

    /**
     * Forwards sql queries to the database
     * @param query sql query
     */
    protected void executeQuery(String query) {

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
        ) {
            statement.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Reads the related information from connection.properties file
     * @param paramNumber which parameter is needed, 1: url, 2: name of database, 3: username, 4: password
     * @return requested information or null if there's an issue
     */
    protected String read(int paramNumber) {
        Properties prop = new Properties();
        InputStream input = null;
        try {

            input = new FileInputStream("src/main/resources/connection.properties");

            prop.load(input);
            if (paramNumber == 1) {
                return prop.getProperty("url");
            } else if (paramNumber == 2) {
                return prop.getProperty("database");
            } else if (paramNumber == 3) {
                return prop.getProperty("user");
            } else if (paramNumber == 4) {
                return prop.getProperty("password");
            } else {
                return null;
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
