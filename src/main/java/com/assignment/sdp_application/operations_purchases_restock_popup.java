package com.assignment.sdp_application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class operations_purchases_restock_popup {
    @FXML
    private ComboBox<String> supplierComboBox;
    @FXML
    private ComboBox<String> productComboBox;
    @FXML
    private TextField priceInput;
    @FXML
    private TextField quantityInput;
    @FXML
    private TextField totalInput;
    @FXML
    private Button addPurchaseButton;
    @FXML
    private Button cancelButton;


    private ObservableList<String> suppliers = FXCollections.observableArrayList();

    public void initialize() {
        //Suppliers in database
        suppliers.addAll("Supplier1","Supplier2","Supplier3","Supplier4","Supplier5");

        //Set suppliers in ComboBox
        supplierComboBox.setItems(suppliers);

        //On choosing an option set product lists and change prompt text
        supplierComboBox.setOnAction(e -> {
            productComboBox.setPromptText("Please select a product");
            productComboBox.setItems(suppliers);

        });

        //Closes window
        cancelButton.setOnAction(e -> {
            try {
                closeWindow(e);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

    }




    public void closeWindow(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("operations_purchases_restock_popup.fxml"));
        Stage stage =(Stage)((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }





}
