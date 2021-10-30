package com.assignment.sdp_application;

import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;

import java.util.ArrayList;

public class incomeStatementPopUp {


    @FXML
    private TreeTableView<IncomeStatement> incomeStatementTable;
    @FXML
    private TreeTableColumn<IncomeStatement,String> incomeStatementItems;
    @FXML
    private TreeTableColumn<IncomeStatement, Double> actualAmount;

    private ArrayList<TreeItem<IncomeStatement>> items = new ArrayList<>();

    //Dummy root for other roots
    private TreeItem<IncomeStatement> root = new TreeItem<>(new IncomeStatement("Dummy"));

    //Main roots
    private TreeItem<IncomeStatement> revenueroot = new TreeItem<>(new IncomeStatement("Revenue"));
    private TreeItem<IncomeStatement> cosroot = new TreeItem<>(new IncomeStatement("Costs of Sales"));
    private TreeItem<IncomeStatement> incomeroot = new TreeItem<>(new IncomeStatement("Additional Income"));
    private TreeItem<IncomeStatement> expenseroot = new TreeItem<>(new IncomeStatement("Expenses"));

    public void loadData(ArrayList<TreeItem<IncomeStatement>> items){

        this.items = items;

        //Expansion of roots
        revenueroot.setExpanded(true);
        cosroot.setExpanded(true);
        incomeroot.setExpanded(true);
        expenseroot.setExpanded(true);

        //Add necessary items to table first
        incomeStatementTable.setRoot(root);
        incomeStatementTable.setShowRoot(false);
        root.getChildren().addAll(revenueroot, cosroot, incomeroot, expenseroot);


        //Set Column 1 type of data
        incomeStatementItems.setCellValueFactory(new TreeItemPropertyValueFactory<>("item"));


        //Set Column 2 type of data
        actualAmount.setCellValueFactory(new TreeItemPropertyValueFactory<>("itemActualAmount"));


        //Add Income Statement Items into table array
        //For loop for database

        //Add table array items into their individual roots
        for(int i = 0; i < this.items.size(); i++){
            revenueroot.getChildren().add(this.items.get(i));
            cosroot.getChildren().add(this.items.get(i));
        }

        //refreshes the table
        incomeStatementTable.refresh();
    }




}
