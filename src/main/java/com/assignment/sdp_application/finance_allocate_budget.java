package com.assignment.sdp_application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;

public class finance_allocate_budget {

    //Elements from FXML file
    @FXML
    private TreeTableView<IncomeStatement> allocatebudgettable;
    @FXML
    private TreeTableColumn<IncomeStatement, String> col1;
    @FXML
    private TreeTableColumn<IncomeStatement, Double> col2;
    @FXML
    private TreeTableColumn<IncomeStatement, Double> col3;
    @FXML
    private Label datelabel;
    @FXML
    private Button homeButton;
    @FXML
    private Button cancelButton;

    private LocalDate isDate = YearMonth.now().atEndOfMonth();

    //Table array for loading data
    private ArrayList<TreeItem<IncomeStatement>> items = new ArrayList<TreeItem<IncomeStatement>>();

    //Dummy root for other roots
    private TreeItem<IncomeStatement> root = new TreeItem<>(new IncomeStatement("Dummy"));

    //Main roots
    private TreeItem<IncomeStatement> revenueroot = new TreeItem<>(new IncomeStatement("Revenue"));
    private TreeItem<IncomeStatement> cosroot = new TreeItem<>(new IncomeStatement("Costs of Sales"));
    private TreeItem<IncomeStatement> incomeroot = new TreeItem<>(new IncomeStatement("Additional Income"));
    private TreeItem<IncomeStatement> expenseroot = new TreeItem<>(new IncomeStatement("Expenses"));


    private Stage stage;
    private Scene scene;
    private Parent stageroot;



    public void initialize() {

        homeButton.setOnAction(e -> {
            try {
                goSomewhere(e, "finance_main.fxml","Finance Department Dashboard");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        cancelButton.setOnAction(e -> {
            try {
                goSomewhere(e, "finance_main.fxml","Finance Department Dashboard");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        //Set Text for Title
        datelabel.setText(datelabel.getText() + YearMonth.now().atEndOfMonth());


        //Expansion of roots
        revenueroot.setExpanded(true);
        cosroot.setExpanded(true);
        incomeroot.setExpanded(true);
        expenseroot.setExpanded(true);

        //Add necessary items to table first
        allocatebudgettable.setRoot(root);
        allocatebudgettable.setShowRoot(false);
        root.getChildren().addAll(revenueroot, cosroot, incomeroot, expenseroot);


        //Set Column 1 type of data
        col1.setCellValueFactory(new TreeItemPropertyValueFactory<>("item"));


        //Set Column 2 type of data
        col2.setCellValueFactory(new TreeItemPropertyValueFactory<>("itemActualAmount"));


        //Set Column 2 type of data
        col3.setCellValueFactory(new TreeItemPropertyValueFactory<>("itemBudgetedAmount"));
        DoubleStringConverter converter = new DoubleStringConverter();
        col3.setCellFactory(TextFieldTreeTableCell.<IncomeStatement,Double>forTreeTableColumn(converter));
        col3.setOnEditCommit(e -> {
            TreeItem<IncomeStatement> currentEditingItem = allocatebudgettable.getTreeItem(e.getTreeTablePosition().getRow());
            currentEditingItem.getValue().setItemBudgetedAmount(e.getNewValue());
        });

        loadData();


    }
    //Loads Data Function
    public void loadData(){
        //Clear all items from the table array
        items.clear();

        //Add Income Statement Items into table array
        //For loop for database
        items.add(new TreeItem<IncomeStatement>(new IncomeStatement("Expenses",2500.0,isDate)));


        //Add table array items into their individual roots
        for(int i = 0 ; i < items.size();i++){
            revenueroot.getChildren().add(items.get(i));
            cosroot.getChildren().add(items.get(i));
        }

        //refreshes the table
        allocatebudgettable.refresh();
    }

    public void goSomewhere(ActionEvent event, String fxml, String title) throws IOException {

        stageroot = FXMLLoader.load(getClass().getResource(fxml));
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setOnCloseRequest(e -> {
            Boolean answer = MessageBoxConfirm.display("Close Application", "Are you sure you want to close the application?");
            if (answer == true) {
                stage.close();
            } else if (answer == false) {
                e.consume();
            }

        });

        scene = new Scene(stageroot,1080,640);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

}
