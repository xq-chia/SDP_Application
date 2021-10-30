package com.assignment.sdp_application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class sales_sales_goal {

    @FXML
    private Button setSalesGoalButton;

    @FXML
    private TextField salesGoalInput;

    @FXML
    private Button homeButton;

    private Stage stage;
    private Scene scene;
    private Parent root;



    public void initialize(){

        homeButton.setOnAction(e -> {
            try {
                goSomewhere(e, "sales_main.fxml","Sales Department Dashboard");
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
