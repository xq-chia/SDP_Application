package com.assignment.sdp_application;

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

public class operations_purchases_addproduct_popup {
    @FXML
    private ComboBox<String> supplierInput;
    @FXML
    private TextField productNameInput;
    @FXML
    private ComboBox<String> productTypeComboBox;
    @FXML
    private TextField initialQuantityInput;
    @FXML
    private TextField sellingPriceInput;

    @FXML
    private Button addProductButton;
    @FXML
    private Button cancelButton;


    public void initialize() {

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
