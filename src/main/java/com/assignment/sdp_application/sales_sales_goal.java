package com.assignment.sdp_application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class sales_sales_goal {

    @FXML
    private Button setSalesGoalButton;

    @FXML
    private TextField salesGoalInput;

    @FXML
    private Button homeButton;

    private Stage stage;
    private Scene scene;
    private Parent root;

    private Connection conn = Database.getConnection();


    public void initialize(){

        homeButton.setOnAction(e -> {
            try {
                goSomewhere(e, "sales_main.fxml","Sales Department Dashboard");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        setSalesGoalButton.setOnAction(e -> {
            setSalesGoal();
        });
    }

    public void setSalesGoal() {
        String sql;
        PreparedStatement prepSql;
        ResultSet result;
        int pkValue, rowInserted;

        pkValue = Database.getPrimaryKeyValue(conn, "income_statement_t");

        sql = "INSERT INTO income_statement_t " +
                "VALUES (?, ?, ?, ?, ?)";
        try {
            prepSql = conn.prepareStatement(sql);

            prepSql.setString(1, Database.getPrimaryKeyString(pkValue, "IS", 8));
            prepSql.setString(2, LocalDate.now().toString());
            prepSql.setString(3, "Sales");
            prepSql.setDouble(4, getActualSales());
            prepSql.setString(5, salesGoalInput.getText());

            System.out.println(prepSql);
            rowInserted = prepSql.executeUpdate();
            if (rowInserted > 0) {
                System.out.println("Inserted sql: " + prepSql);
            } else {
                System.out.println("failed");
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
    }

    public double getActualSales() {
        String sql;
        Statement statement;
        ResultSet result;
        LocalDate startOfprevMonth, endOfprevMonth;
        double ret;

        ret = 0;
        startOfprevMonth = LocalDate.now().minusMonths(1).withDayOfMonth(1);
        endOfprevMonth = LocalDate.now().minusMonths(1).withDayOfMonth(startOfprevMonth.lengthOfMonth());

        sql = "SELECT SUM(sls_price) AS 'sales' " +
                "FROM sales_t " +
                "WHERE sls_datetime >= '" + startOfprevMonth + "' AND " +
                "sls_datetime <= '" + endOfprevMonth + "'";
        try {
            statement = conn.createStatement();
            result = statement.executeQuery(sql);

            if (result.next()) {
                ret = result.getDouble("sales");
            } else {
                ret = 0.0;
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }

        return ret;
    }

    public void goSomewhere(ActionEvent e, String fxml, String title) throws IOException {
        root = FXMLLoader.load(getClass().getResource(fxml));
        stage =(Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root,1080,640);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();

    }

}
