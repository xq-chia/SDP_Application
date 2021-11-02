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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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


        //Format into DateTime
        //Need Database Query with Calculation Function
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatted = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = now.format(formatted);

        System.out.println(formattedDate);

        LocalDateTime Dateformatted = LocalDateTime.parse(formattedDate,formatted);

        //Create a new Sales Object as sample
        salesItems.add(new Sales("S000001","Jeans for Life", 2,100,Dateformatted));


        //Load Object onto Table
        mostSoldProductsTable.setItems(salesItems);


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
