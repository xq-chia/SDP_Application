package com.assignment.sdp_application;

import java.sql.*;

public class Database {
    public static Connection getConnection() {
        String databaseURL, username, password;
        Connection conn;

        databaseURL = "jdbc:mysql://localhost:3306/sdp";
        username = "root";
        password = "";
        conn = null;

        try {
            conn = DriverManager.getConnection(databaseURL, username, password);
        } catch (SQLException ex) {
            System.out.println("Failed to connect to database: " + ex);
        }
        return conn;
    }

    public static int getPrimaryKeyValue(Connection conn, String table) {
        String sql;
        Statement statement;
        ResultSet result;
        int pkValue;

        pkValue = 0;

        try {
            sql = "SELECT COUNT(*) AS 'rowNum' FROM " + table;
            statement = conn.createStatement();
            result = statement.executeQuery(sql);

            if (result.next()) {
                pkValue = result.getInt("rowNum") + 1;
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }

        return pkValue;
    }

    public static String getPrimaryKeyString(int pkValue, String prefix, int length) {
        return String.format("%s%0" + (length - prefix.length()) + "d", prefix, pkValue);
    }
}