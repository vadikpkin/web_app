package com.epam.dbconnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String URL = "jdbc:postgresql:ad_service_db";
    private static final String USER = "macbook";
    private static final String PASS = "1234567";

    public static Connection getConnection() {

        try {

            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException e) {

            throw new IllegalStateException("BaseDataSource is unable to load org.postgresql.Driver. " +
                    "please check if you have proper PostgreSQL JDBC Driver jar on the classpath", e);

        }

        try {

            return DriverManager.getConnection(URL, USER, PASS);

        } catch (SQLException e) {

            throw new RuntimeException("Error connecting to the database", e);

        }
    }
}
