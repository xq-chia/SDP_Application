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

public class finance_main {
    @FXML
    private Button allocateBudgetButton;

    @FXML
    private Button budgetingReportsButton;

    @FXML
    private Button logoutButton;

    private Stage stage;
    private Scene scene;
    private Parent root;


    public void initialize() {
        allocateBudgetButton.setOnAction(e -> {
            try {
                goSomewhere(e, "finance_allocate_budget.fxml","Allocate Budget");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        budgetingReportsButton.setOnAction(e -> {
            try {
                goSomewhere(e, "finance_budgeting_reports.fxml","Budgeting Reports");
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
