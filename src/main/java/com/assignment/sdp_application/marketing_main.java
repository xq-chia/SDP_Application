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

public class marketing_main {

    @FXML
    private Button promotionsButton;
    @FXML
    private Button researchButton;


    private Stage stage;
    private Scene scene;
    private Parent root;

    public void initialize(){

        promotionsButton.setOnAction(e -> {

        });
        researchButton.setOnAction(e -> {

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
