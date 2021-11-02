package com.assignment.sdp_application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class finance_budgeting_reports {
    @FXML
    private TreeTableView<BudgetingReport> budgetingReportTable;
    @FXML
    private TreeTableColumn<BudgetingReport,Integer> yearList;
    @FXML
    private TreeTableColumn<BudgetingReport, String> monthList;
    @FXML
    private Button homeButton;


    //get end of the month
    private LocalDate date = YearMonth.now().atEndOfMonth();

    //an arraylist to gather all treeitems for income statement
    private ArrayList<TreeItem<IncomeStatement>> items = new ArrayList<>();


    private Stage stage;
    private Scene scene;
    private Parent root;

    private TreeItem<BudgetingReport> dummy = new TreeItem<>(new BudgetingReport());

    public void initialize() throws IOException {
        yearList.setCellValueFactory(new TreeItemPropertyValueFactory<>("year"));
        monthList.setCellValueFactory(new TreeItemPropertyValueFactory<>("months"));

        //add tree items to an array
        items.add(new TreeItem<IncomeStatement>(new IncomeStatement("Expenses", 2500.0, 1000.0, date)));
        items.add(new TreeItem<IncomeStatement>(new IncomeStatement("Expenses", 2500.0, 1000.0, date)));
        items.add(new TreeItem<IncomeStatement>(new IncomeStatement("Expenses", 2500.0, 1000.0, date)));

        //Dummy root
        budgetingReportTable.setRoot(dummy);
        budgetingReportTable.setShowRoot(false);

        //Get year roots
        dummy.getChildren().add(new TreeItem<>(new BudgetingReport(2021)));
        dummy.getChildren().add(new TreeItem<>(new BudgetingReport(2022)));
        dummy.getChildren().add(new TreeItem<>(new BudgetingReport(2023)));
        dummy.getChildren().add(new TreeItem<>(new BudgetingReport(2024)));
        dummy.getChildren().add(new TreeItem<>(new BudgetingReport(2025)));


        //Insert months into years
        for (int i = 0; i < dummy.getChildren().size(); i++) {
            Calendar months = Calendar.getInstance(Locale.getDefault());
            for (int j = Calendar.JANUARY; j <= Calendar.DECEMBER; j++) {
                months.set(Calendar.MONTH, j);
                String month = months.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
                dummy.getChildren().get(i).getChildren().add(new TreeItem<>(new BudgetingReport(month)));
            }
        }


        homeButton.setOnAction(e -> {
            try {
                goSomewhere(e, "finance_main.fxml", "Finance Department Dashboard");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });


        //Double click record to display pop up of income statement
        budgetingReportTable.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                TreeItem<BudgetingReport> sample = budgetingReportTable.getSelectionModel().getSelectedItem();

                if (sample.getValue().getYear() == 0) {
                    System.out.println(sample.getParent().getValue().getYear());
                    System.out.println(sample.getValue().getMonths());

                    //add items into items array
                    //load data into an budgeting report object

                    try {
                        loaddata(items);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });


    }


    private void loaddata(ArrayList<TreeItem<IncomeStatement>> data) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("budgetingReportPopUp.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        budgetingReportPopUp controller = fxmlLoader.getController();
        controller.loadData(data);

        Stage stage = new Stage();
        stage.setTitle("Sample");
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();

    }


    public void goSomewhere(ActionEvent event, String fxml, String title) throws IOException {

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
