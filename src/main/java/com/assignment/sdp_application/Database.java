package com.assignment.sdp_application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    public static Connection getConnection() {
        String databaseURL, username, password;
        Connection conn;

        databaseURL = "jdbc:mysql://localhost:3306/java";
        username = "root";
        password = "";
        conn = null;

        try {
            conn = DriverManager.getConnection(databaseURL, username, password);
        } catch (SQLException ex) {
            System.out.println("Failed to connect to database: " + ex);
        }
        System.out.println("Connected");
        return conn;
    }
}
