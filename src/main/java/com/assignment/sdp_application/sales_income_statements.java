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
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;

public class sales_income_statements {
    @FXML
    private Button homeButton;
    @FXML
    private TableView<BudgetingReport> incomeStatementTable;
    @FXML
    private TableColumn<BudgetingReport, LocalDate> incomeStatementColumn;

    private ObservableList<BudgetingReport> salesItems = FXCollections.observableArrayList();

    private Stage stage;
    private Scene scene;
    private Parent root;

    private LocalDate dates = YearMonth.now().atEndOfMonth();
    private ArrayList<TreeItem<IncomeStatement>> items = new ArrayList<>();

    public void initialize(){

        //Set Column Data Type
        incomeStatementColumn.setCellValueFactory(new PropertyValueFactory<>("incomestatement_dates"));

        homeButton.setOnAction(e -> {
            try {
                goSomewhere(e,"sales_main.fxml","Sales Department Dashboard");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });


        //Add Tree Items into array which includes Income Statement Objects

        items.add(new TreeItem<IncomeStatement>(new IncomeStatement("Expenses",1000.0,dates)));
        items.add(new TreeItem<IncomeStatement>(new IncomeStatement("Expenses",1000.0,dates)));
        items.add(new TreeItem<IncomeStatement>(new IncomeStatement("Expenses",1000.0,dates)));


        //get Date from the Object as all of them should be the same
        //Need adjustment
        //salesItems.add(new BudgetingReport(items,YearMonth.of(items.get(0).getValue().getItemDate().getYear(),items.get(0).getValue().getItemDate().getMonth())));


        //Load Object into Table
        incomeStatementTable.setItems(salesItems);

        incomeStatementTable.setOnMouseClicked(e -> {

            BudgetingReport sample = (BudgetingReport) incomeStatementTable.getSelectionModel().getSelectedItem();
            try {
                loaddata(sample.getIncomestatement_items());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });


    }

    private void loaddata(ArrayList<TreeItem<IncomeStatement>> data) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("incomeStatementPopUp.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        incomeStatementPopUp controller = fxmlLoader.getController();
        controller.loadData(data);

        Stage stage = new Stage();
        stage.setTitle("Sample");
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();

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
