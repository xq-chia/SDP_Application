package com.assignment.sdp_application;

import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.util.converter.DoubleStringConverter;

import java.util.ArrayList;

public class budgetingReportPopUp {
    @FXML
    private TreeTableView<IncomeStatement> budgetingReportTable;
    @FXML
    private TreeTableColumn<IncomeStatement, String> incomestatementitems;
    @FXML
    private TreeTableColumn<IncomeStatement, Double> budgetedAmount;
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
        //Expansion of roots
        revenueroot.setExpanded(true);
        cosroot.setExpanded(true);
        incomeroot.setExpanded(true);
        expenseroot.setExpanded(true);

        //Add necessary items to table first
        budgetingReportTable.setRoot(root);
        budgetingReportTable.setShowRoot(false);
        root.getChildren().addAll(revenueroot, cosroot, incomeroot, expenseroot);


        //Set Column 1 type of data
        incomestatementitems.setCellValueFactory(new TreeItemPropertyValueFactory<>("item"));


        //Set Column 2 type of data
        actualAmount.setCellValueFactory(new TreeItemPropertyValueFactory<>("itemActualAmount"));


        //Set Column 2 type of data
        budgetedAmount.setCellValueFactory(new TreeItemPropertyValueFactory<>("itemBudgetedAmount"));
        budgetedAmount.setCellFactory(TextFieldTreeTableCell.<IncomeStatement, Double>forTreeTableColumn(new DoubleStringConverter()));
        budgetedAmount.setOnEditCommit(e -> {
            TreeItem<IncomeStatement> currentEditingItem = budgetingReportTable.getTreeItem(e.getTreeTablePosition().getRow());
            currentEditingItem.getValue().setItemBudgetedAmount(Double.valueOf(e.getNewValue()));
        });
        this.items = items;
        //Clear all items from the table array

        //Add Income Statement Items into table array
        //For loop for database

        //Add table array items into their individual roots
        for(int i = 0; i < this.items.size(); i++){
            revenueroot.getChildren().add(this.items.get(i));
            cosroot.getChildren().add(this.items.get(i));
        }

        //refreshes the table
        budgetingReportTable.refresh();
    }

}
