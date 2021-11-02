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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class operations_purchases {
    @FXML
    private TableView<Purchases> purchaseTable;
    @FXML
    private TableColumn<Purchases,String> purchaseIdColumn;
    @FXML
    private TableColumn<Purchases,String> productNameColumn;
    @FXML
    private TableColumn<Purchases,String> supplierColumn;
    @FXML
    private TableColumn<Purchases,Double> priceColumn;
    @FXML
    private TableColumn<Purchases,Integer> quantityColumn;
    @FXML
    private TableColumn<Purchases,Double> costColumn;
    @FXML
    private TableColumn<Purchases,Double> discountColumn;

    @FXML
    private Button homeButton;
    @FXML
    private Button restockProductsButton;
    @FXML
    private Button supplierListButton;

    private Stage stage;
    private Scene scene;
    private Parent root;


    private ObservableList<Purchases> purchases = FXCollections.observableArrayList();

    private Connection conn = Database.getConnection();

    public void initialize(){
        purchaseIdColumn.setCellValueFactory(new PropertyValueFactory<>("purchaseId"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        supplierColumn.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        costColumn.setCellValueFactory(new PropertyValueFactory<>("totalcost"));
        discountColumn.setCellValueFactory(new PropertyValueFactory<>("discount"));


        loadData();

        purchaseTable.setItems(purchases);

        homeButton.setOnAction(e -> {
            try {
                goSomewhere(e,"operations_main.fxml","Operations Department Dashboard");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

//        supplierListButton.setOnAction();
        restockProductsButton.setOnAction(e -> {
            try {
                purchasePopUp();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

    }

    public void loadData() {
        String sql;
        Statement statement;
        ResultSet result;
        LocalDate startOfMonth = LocalDate.now().withDayOfMonth(1);

        sql = "SELECT * " +
                "FROM purchase_order_t po, supplier_t s, product_t p " +
                "WHERE po.prod_id = p.prod_id AND " +
                "s.sup_id = po.sup_id AND " +
                "po.po_date >= '" + startOfMonth + "'";
        try {
            statement = conn.createStatement();
            result = statement.executeQuery(sql);

            while (result.next()) {
                String id, name, supplier;
                Double price, cost, discount;
                int quantity;

                id = result.getString("po_id");
                name = result.getString("prod_name");
                supplier = result.getString("sup_name");
                price = result.getDouble("po_unit_price");
                quantity = result.getInt("po_quantity");
                discount = result.getDouble("po_discount");
                cost = price  * quantity - discount;

                purchases.add(new Purchases(id, name, supplier, price, quantity, cost, discount));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
    }

    public void purchasePopUp() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("operations_purchases_popup.fxml"));
        Parent root = (Parent) fxmlLoader.load();

        Stage stage = new Stage();
        stage.setTitle("Restock Product");
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOpacity(0.98);
        stage.setResizable(false);
        stage.show();
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
