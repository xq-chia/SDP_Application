package com.assignment.sdp_application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class pos_addsales {
    @FXML
    private TableView<Cart> cartTable;
    @FXML
    private TableColumn<Cart,String> productNameCartColumn;
    @FXML
    private TableColumn<Cart,String> quantityCartColumn;
    @FXML
    private TableColumn<Cart,Double> priceCartColumn;

    @FXML
    private TableView<Product> productsTable;
    @FXML
    private TableColumn<Product,String> productNameProductColumn;
    @FXML
    private TableColumn<Product,String>  productTypeProductColumn;
    @FXML
    private TableColumn<Product,Double> priceProductColumn;
    @FXML
    private TextField searchInput;
    @FXML
    private Button addSalesButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Label subtotalLabel;
    @FXML
    private Label taxLabel;
    @FXML
    private Label totalLabel;

    private double subtotal = 0;
    private double salestax = 0;
    private double finaltotal = 0;

    private ObservableList<Product> products = FXCollections.observableArrayList();
    private ObservableList<Cart> cart = FXCollections.observableArrayList();

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void initialize(){


        productNameCartColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        quantityCartColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceCartColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        productNameProductColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        productTypeProductColumn.setCellValueFactory(new PropertyValueFactory<>("productType"));
        priceProductColumn.setCellValueFactory(new PropertyValueFactory<>("productPrice"));

        cancelButton.setOnAction(e -> {
            try {
                goSomewhere(e,"pos_main.fxml","POS Dashboard");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        products.add(new Product("P000001","GoodLife","Jeannie",30,true,"Jeans",50));

        productsTable.setItems(products);

        productsTable.setOnMouseClicked(e -> {
            if(e.getClickCount() == 2){
                Product addToCart = productsTable.getSelectionModel().getSelectedItem();

                if(cart.stream().filter(o -> o.getProductName().equals(addToCart.getProductName())).findFirst().isPresent()){
                    for (int i = 0; i< cart.size(); i++){
                        if (addToCart.getProductName().equals(cart.get(i).getProductName())){
                            cart.get(i).setQuantity(cart.get(i).getQuantity()+1);
                            cart.get(i).setPrice(cart.get(i).getQuantity() * addToCart.getProductPrice());
                        }
                    }
                }

                else{
                    cart.add(new Cart(addToCart.getProductName(), 1, addToCart.getProductPrice(), addToCart.getProductPrice()));
                }

                cartTable.setItems(cart);
                cartTable.refresh();


                for(int i = 0; i <cart.size(); i++){
                    subtotal += cart.get(i).getPrice();
                }
                salestax = subtotal* 0.1;
                finaltotal = subtotal + salestax;

                subtotalLabel.setText("RM " + String.valueOf(subtotal));
                taxLabel.setText("RM " + String.valueOf(salestax));
                totalLabel.setText("RM " + String.valueOf(finaltotal));


            }

        });

        cartTable.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2){
                Cart removeFromCart = cartTable.getSelectionModel().getSelectedItem();

                if (removeFromCart.getQuantity() > 1){
                    removeFromCart.setQuantity(removeFromCart.getQuantity()-1);
                    removeFromCart.setPrice(removeFromCart.getPrice() - removeFromCart.getOriprice());
                }
                else{
                cart.remove(removeFromCart);
                }

                for(int i = 0; i <cart.size(); i++){
                    subtotal -= cart.get(i).getPrice();
                }
                salestax = subtotal* 0.1;
                finaltotal = subtotal + salestax;

                subtotalLabel.setText("RM " + String.valueOf(subtotal));
                taxLabel.setText("RM " + String.valueOf(salestax));
                totalLabel.setText("RM " + String.valueOf(finaltotal));

                cartTable.setItems(cart);
                cartTable.refresh();
            }
        });


    }


    public void setPrice(){

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
