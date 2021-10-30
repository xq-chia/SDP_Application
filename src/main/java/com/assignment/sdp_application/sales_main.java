package com.assignment.sdp_application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class sales_main {
    @FXML
    private Button viewCurrentSalesButton;
    @FXML
    private Button setRevenueGoalButton;
    @FXML
    private Button incomeStatementsButton;
    @FXML
    private Button logoutButton;

    private Stage stage;
    private Scene scene;
    private Parent root;


    public void initialize(){

        viewCurrentSalesButton.setOnAction(e -> {
            try {
                goSomewhere(e,"sales_current_sales.fxml","Current Sales");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        setRevenueGoalButton.setOnAction(e -> {
            try {
                goSomewhere(e,"sales_sales_goal.fxml","Set Sales Goal");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        incomeStatementsButton.setOnAction(e -> {
            try {
                goSomewhere(e,"sales_income_statements.fxml","Income Statements");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        logoutButton.setOnAction(e -> {
            try {
                Boolean answer = MessageBoxConfirm.display("Logout","Are you sure you want to logout?");
                if(answer){
                    goSomewhere(e, "login.fxml","Login");
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

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
