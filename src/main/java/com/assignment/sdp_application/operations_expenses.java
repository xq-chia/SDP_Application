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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;

import java.io.IOException;
import java.time.LocalDate;

public class operations_expenses {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableView<Expenses> expenseTable;
    @FXML
    private TableColumn<Expenses,String> expenseColumn;
    @FXML
    private TableColumn<Expenses, Double> amountColumn;
    @FXML
    private Button addExpenseButton;
    @FXML
    private Button homeButton;

    private ObservableList<Expenses> expenses = FXCollections.observableArrayList();


    public void initialize(){
        expenseColumn.setCellValueFactory(new PropertyValueFactory<>("expenseName"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("expenseAmount"));
        expenseColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        amountColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        expenseColumn.setOnEditCommit(e -> {
            Expenses expense = e.getRowValue();
            expense.setExpenseName(e.getNewValue());
            System.out.println(expense.getExpenseName());
        });

        amountColumn.setOnEditCommit(e -> {
            Expenses expense = e.getRowValue();
            expense.setExpenseAmount(e.getNewValue());
            System.out.println(expense.getExpenseAmount());
        });


        homeButton.setOnAction(e -> {
            try {
                goSomewhere(e,"operations_main.fxml","Operations Department Dashboard");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        expenses.add(new Expenses("Rent",500.0, LocalDate.now()));
        expenses.add(new Expenses("Electricity Fees",500.0, LocalDate.now()));
        expenses.add(new Expenses("Water Fees",500.0, LocalDate.now()));
        expenses.add(new Expenses("Wifi Fees",500.0, LocalDate.now()));

        expenseTable.setItems(expenses);

        addExpenseButton.setOnAction(e -> {
            expenses.add(new Expenses(LocalDate.now()));
            expenseTable.setItems(expenses);
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
