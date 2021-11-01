package com.assignment.sdp_application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class operations_purchases_popup {
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

        suppliers.addAll("Supplier1","Supplier2","Supplier3","Supplier4","Supplier5");


        supplierComboBox.setItems(suppliers);

        supplierComboBox.setOnAction(e -> {
            productComboBox.setItems(suppliers);

        });

    }





}
