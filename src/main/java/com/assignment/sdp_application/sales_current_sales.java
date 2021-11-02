package com.assignment.sdp_application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class sales_current_sales {
    @FXML
    private Button homeButton;
    @FXML
    private Label currentSalesLabel;
    @FXML
    private TableView<Sales> mostSoldProductsTable;
    @FXML
    private TableColumn<Sales,String> productNameColumn;
    @FXML
    private TableColumn<Sales,Integer> amountSoldColumn;

    private Stage stage;
    private Scene scene;
    private Parent root;


    //Table Array
    private ObservableList<Sales> salesItems = FXCollections.observableArrayList();

    private Connection conn = Database.getConnection();

    public void initialize(){


        //Set Coloumn Data Type
        productNameColumn.setCellValueFactory(new PropertyValueFactory("productName"));
        amountSoldColumn.setCellValueFactory(new PropertyValueFactory("salesAmount"));


        homeButton.setOnAction(e -> {
            try {
                goSomewhere(e,"sales_main.fxml","Sales Department Dashboard");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });


        loadData();

        //Load Object onto Table
        mostSoldProductsTable.setItems(salesItems);
    }

    public void loadData() {
        loadCurrentSales();
        loadMostSoldProduct();
    }

    public void loadCurrentSales() {
        String sql, currentSales;
        Statement statement;
        ResultSet result;
        LocalDate startOfMonth = LocalDate.now().withDayOfMonth(1);

        sql = "SELECT SUM(sls_price) AS 'total' " +
                "FROM sales_t " +
                "WHERE sls_datetime >= '" + startOfMonth.toString() + "'";
        currentSales = "";
        try {
            statement = conn.createStatement();
            result = statement.executeQuery(sql);

            if (result.next()) {
                if (result.getString("total") == "null") {
                    currentSales = currentSalesLabel.getText() + result.getString("total");
                } else {
                    currentSales = currentSalesLabel.getText() + String.valueOf(0.00);
                }
            }
            currentSalesLabel.setText(currentSales);
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
    }

    public void loadMostSoldProduct() {
        String sql;
        Statement statement;
        ResultSet result;
        LocalDate past30days;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        past30days= LocalDate.now().minusDays(30);
        sql = "SELECT t.sls_id, p.prod_name, t.quantity, t.amount, t.sls_datetime " +
                "FROM product_t p, (SELECT sls_id, prod_id, SUM(sls_quantity) as 'quantity', SUM(sls_price) as 'amount', sls_datetime " +
                "                   FROM sales_t " +
                "                   GROUP BY prod_id) t " +
                "WHERE p.prod_id = t.prod_id AND " +
                "t.sls_datetime >= '" + past30days.toString() + "' " +
                "ORDER BY t.amount DESC " +
                "LIMIT 10";
        try {
            statement = conn.createStatement();
            result = statement.executeQuery(sql);

            while (result.next()) {
                String salesId, productName;
                int quantity;
                Double amount;
                LocalDateTime dateTime;
                Sales item;

                salesId = result.getString("sls_id");
                productName = result.getString("prod_name");
                quantity = result.getInt("quantity");
                amount = result.getDouble("amount");

                System.out.println(result.getString("sls_datetime"));

                dateTime = LocalDateTime.parse(result.getString("sls_datetime"), formatter);

                item = new Sales(salesId, productName, quantity, amount, dateTime);
                salesItems.add(item);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
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
