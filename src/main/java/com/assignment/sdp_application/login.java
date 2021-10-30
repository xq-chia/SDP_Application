package com.assignment.sdp_application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class login {
    @FXML
    private TextField userinput;

    @FXML
    private PasswordField passinput;

    private Stage stage;
    private Scene scene;
    private Parent root;

    private List<String> userlist = Arrays.asList("finance","sales","marketing","operations","hr","pos");
    private List<String> passwordlist = Arrays.asList("finance","sales","marketing","operations","hr","pos");

    private ArrayList<String> users = new ArrayList<String>();
    private ArrayList<String> passwords = new ArrayList<String>();

    public void initialize(){
        users.addAll(userlist);
        passwords.addAll(passwordlist);
    }

    public void verification(ActionEvent e) throws IOException {
        String user = userinput.getText().trim();
        String pass = passinput.getText().trim();


        if(user.isBlank()){
            userinput.setStyle("-fx-border-color: red;");
        }

        else{
            userinput.setStyle("-fx-border-color: black;");
        }

        if(pass.isBlank()){
            passinput.setStyle("-fx-border-color: red;");
        }
        else{
            passinput.setStyle("-fx-border-color: black;");
        }

        if (user.isEmpty() == false && pass.isEmpty()== false){
            authentication(e, user, pass);
        }

    }


    public void authentication(ActionEvent e, String user, String pass) throws IOException {
        if(users.contains(user)){
            if(pass.equals(passwords.get(users.indexOf(user)))){
                if(user.equals("finance")){
                    goSomewhere(e,"finance_main.fxml","Finance Department Dashboard");
                }

                else if(user.equals("sales")){
                    goSomewhere(e,"sales_main.fxml","Sales Department Dashboard");
                }

                else if(user.equals("operations")){
                    goSomewhere(e,"operations_main.fxml","Operations Department Dashboard");
                }

                else if(user.equals("pos")){
                    goSomewhere(e,"pos_main.fxml","POS Dashboard");
                }

            }

            else{
                MessageBox.display("Invalid Password","The Password is Incorrect");
                passinput.setStyle("-fx-border-color: red;");
                passinput.setText("");
            }
        }

        else{
            MessageBox.display("Invalid User","The Username is Invalid");
            userinput.setStyle("-fx-border-color: red;");
            userinput.setText("");

        }

    }


    public void goSomewhere(ActionEvent event,String fxml, String title) throws IOException {

        root = FXMLLoader.load(getClass().getResource(fxml));
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setOnCloseRequest(e -> {
                    Boolean answer = MessageBoxConfirm.display("Close Application", "Are you sure you want to close the application?");
                    if (answer == true) {
                        stage.close();
                    } else if (answer == false) {
                        e.consume();
                    }

                });

        scene = new Scene(root,1080,640);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

}
