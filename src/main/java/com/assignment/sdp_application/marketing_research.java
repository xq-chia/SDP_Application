package com.assignment.sdp_application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class marketing_research {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button homeButton;
    @FXML
    private Button addResearchButton;
    @FXML
    private TableView<Research> researchTable;
    @FXML
    private TableColumn<Research, String> researchIdColumn;
    @FXML
    private TableColumn<Research, String> researchNameColumn;
    @FXML
    private TableColumn<Research, String> researchCostColumn;


    public void initialize(){
        homeButton.setOnAction(e -> {
            try {
                goSomewhere(e, "marketing_main.fxml","Marketing Department Dashboard");
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
