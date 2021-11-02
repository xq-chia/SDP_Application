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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class pos_inventory {
    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product,String> productColumn;
    @FXML
    private TableColumn<Product, String> productTypeColumn;
    @FXML
    private TableColumn<Product,Integer> quantityColumn;
    @FXML
    private TableColumn<Product,String> supplierColumn;

    @FXML
    private TextField searchInput;

    @FXML
    private Button homeButton;


    private Stage stage;
    private Scene scene;
    private Parent root;


    private ObservableList<Product> products = FXCollections.observableArrayList();

    public void initialize(){

        //Set Column Data Type
        productColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));
        productTypeColumn.setCellValueFactory(new PropertyValueFactory<>("productType"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("productQuantity"));
        supplierColumn.setCellValueFactory(new PropertyValueFactory<>("supplierName"));

        homeButton.setOnAction(e -> {
            try {
                goSomewhere(e, "pos_main.fxml","POS Department Dashboard");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });



        //Sample Data later replaced with function to gather data
        products.add(new Product("PRD000001","Good Life","Jeans 4 Life",30,true,"Jeans",500));


        //Load data into table
        productTable.setItems(products);


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
