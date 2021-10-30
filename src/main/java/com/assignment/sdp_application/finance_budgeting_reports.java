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

public class finance_budgeting_reports {
    @FXML
    private TableView budgetingReportTable;
    @FXML
    private TableColumn<IncomeStatement, LocalDate> isDate;
    @FXML
    private Button homeButton;


    //get end of the month
    private LocalDate dates = YearMonth.now().atEndOfMonth();

    //an arraylist to gather all treeitems for income statement
    private ArrayList<TreeItem<IncomeStatement>> items = new ArrayList<>();

    //Table Array
    //budgetingReport class is created to cluster all the items in the income statement to a date
    private ObservableList<budgetingReport> incomestatementdates = FXCollections.observableArrayList();


    private Stage stage;
    private Scene scene;
    private Parent root;

    public void initialize() throws IOException {
        homeButton.setOnAction(e -> {
            try {
                goSomewhere(e, "finance_main.fxml","Finance Department Dashboard");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });


        //add tree items to an array
        items.add(new TreeItem<IncomeStatement>(new IncomeStatement("Expenses",2500.0,1000.0,dates)));
        items.add(new TreeItem<IncomeStatement>(new IncomeStatement("Expenses",2500.0,1000.0,dates)));
        items.add(new TreeItem<IncomeStatement>(new IncomeStatement("Expenses",2500.0,1000.0,dates)));


        System.out.println(items.get(0).getValue().getItem());
        System.out.println(items.get(0).getValue().getItemActualAmount());
        System.out.println(items.get(0).getValue().getItemBudgetedAmount());

        //Create object for a cluster
        budgetingReport test1 = new budgetingReport(items,items.get(0).getValue().getItemDate());

        //add object to table array
        incomestatementdates.add(test1);

        //Display table items to the screen
        budgetingReportTable.setItems(incomestatementdates);

        //set data type for column
        isDate.setCellValueFactory(new PropertyValueFactory<>("incomestatement_dates"));


        //Double click record to display pop up of income statement
        budgetingReportTable.setOnMouseClicked(e -> {
            if(e.getClickCount() == 2) {
                budgetingReport sample = (budgetingReport) budgetingReportTable.getSelectionModel().getSelectedItem();
                try {
                    loaddata(sample.getIncomestatement_items());
                } catch (IOException ex) {
                    ex.printStackTrace();
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
