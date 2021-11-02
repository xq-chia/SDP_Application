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
import java.sql.*;
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
    @FXML
    private Button allocateButton;

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

    private Connection conn = null;


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

        allocateButton.setOnAction(e -> {
            try {
                allocateBudget();
            } catch (SQLException ex) {
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

        conn = Database.getConnection();

        loadData();
    }

    //Loads Data Function
    public void loadData(){
        String sql;
        Statement statement;
        ResultSet result;
        LocalDate prevMonthStartDate, prevMonthEndDate;

        prevMonthStartDate = LocalDate.now()
                                      .minusMonths(1)
                                      .withDayOfMonth(1);
        prevMonthEndDate = LocalDate.now()
                                    .minusMonths(1)
                                    .withDayOfMonth(prevMonthStartDate.lengthOfMonth());
        //Clear all items from the table array
        items.clear();

        //Add Income Statement Items into table array
        //For loop for database
        try {
            statement = conn.createStatement();

            {
                sql = "SELECT SUM(sls_price * sls_quantity) AS 'sales'" +
                        "FROM sales_t " +
                        "WHERE sls_datetime >= '" + prevMonthStartDate + "' AND " +
                        "sls_datetime <= '" + prevMonthEndDate + "'";
                result = statement.executeQuery(sql);

                while (result.next()) {
                    IncomeStatement item;
                    String name;
                    Double actual;
                    LocalDate date;

                    name = "Sales";
                    actual = result.getDouble("sales");
                    date = LocalDate.now();

                    item = new IncomeStatement(name, actual, date);

                    items.add(new TreeItem<IncomeStatement>(item));
                }
            }

            {
                sql = "SELECT SUM(po_unit_price * po_quantity) AS 'purchases'" +
                        "FROM purchase_order_t " +
                        "WHERE po_date >= '" + prevMonthStartDate + "' AND " +
                        "po_date <= '" + prevMonthEndDate + "'";
                result = statement.executeQuery(sql);

                while (result.next()) {
                    IncomeStatement item;
                    String name;
                    Double actual;
                    LocalDate date;

                    name = "Purchases";
                    actual = result.getDouble("purchases");
                    date = LocalDate.now();

                    item = new IncomeStatement(name, actual, date);

                    items.add(new TreeItem<IncomeStatement>(item));
                }
            }

            {
                sql = "SELECT SUM(po_discount) AS 'bulk_discount' " +
                        "FROM purchase_order_t " +
                        "WHERE po_date >= '" + prevMonthStartDate + "' AND " +
                        "po_date <= '" + prevMonthEndDate + "'";
                result = statement.executeQuery(sql);

                while (result.next()) {
                    IncomeStatement item;
                    String name;
                    Double actual;
                    LocalDate date;

                    name = "Bulk Discount";
                    actual = result.getDouble("bulk_discount");
                    date = LocalDate.now();

                    item = new IncomeStatement(name, actual, date);

                    items.add(new TreeItem<IncomeStatement>(item));
                }
            }

            {
                sql = "SELECT * " +
                        "FROM expenses_t " +
                        "WHERE exp_date >= '" + prevMonthStartDate + "' AND " +
                        "exp_date <= '" + prevMonthEndDate + "'";
                result = statement.executeQuery(sql);

                while (result.next()) {
                    IncomeStatement item;
                    String name;
                    Double actual;
                    LocalDate date;

                    name = result.getString("exp_type");
                    actual = result.getDouble("exp_amount");
                    date = LocalDate.now();

                    item = new IncomeStatement(name, actual, date);

                    items.add(new TreeItem<IncomeStatement>(item));
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }



        //Add table array items into their individual roots
        revenueroot.getChildren().add(items.get(0));
        cosroot.getChildren().add(items.get(1));
        incomeroot.getChildren().add(items.get(2));

        for(int i = 3; i < items.size(); i++){
            expenseroot.getChildren().add(items.get(i));
        }

        //refreshes the table
        allocatebudgettable.refresh();
    }

    public void allocateBudget() throws SQLException {
        String sql;
        PreparedStatement prepSql;
        int rowInserted, pkValue;
        TreeItem<IncomeStatement> item;

        {
            sql = "INSERT INTO income_statement_t " +
                    "VALUES (?, ?, ?, ?, ?)";
            prepSql = conn.prepareStatement(sql);
            item = items.get(0);
            pkValue = Database.getPrimaryKeyValue(conn, "income_statement_t");
            prepSql.setString(1, Database.getPrimaryKeyString(pkValue, "IS", 8));
            prepSql.setString(2, item.getValue().getItemDate().toString());
            prepSql.setString(3, item.getValue().getItem());
            prepSql.setString(4, item.getValue().getItemActualAmount().toString());
            prepSql.setString(5, Double.toString(col3.getCellData(1)));
            System.out.println(prepSql);
            rowInserted = prepSql.executeUpdate();

            if (rowInserted > 0) {
                System.out.println("Inserted sql: " + prepSql);
            } else {
                System.out.println("failed: ");
            }
        }

        {
            sql = "INSERT INTO income_statement_t " +
                    "VALUES (?, ?, ?, ?, ?)";
            prepSql = conn.prepareStatement(sql);
            item = items.get(1);
            pkValue = Database.getPrimaryKeyValue(conn, "income_statement_t");
            prepSql.setString(1, Database.getPrimaryKeyString(pkValue, "IS", 8));
            prepSql.setString(2, item.getValue().getItemDate().toString());
            prepSql.setString(3, item.getValue().getItem());
            prepSql.setString(4, item.getValue().getItemActualAmount().toString());
            prepSql.setString(5, Double.toString(col3.getCellData(3)));
            System.out.println(prepSql);
            rowInserted = prepSql.executeUpdate();

            if (rowInserted > 0) {
                System.out.println("Inserted sql: " + prepSql);
            } else {
                System.out.println("failed: ");
            }
        }

        {
            sql = "INSERT INTO income_statement_t " +
                    "VALUES (?, ?, ?, ?, ?)";
            prepSql = conn.prepareStatement(sql);
            item = items.get(2);
            pkValue = Database.getPrimaryKeyValue(conn, "income_statement_t");
            prepSql.setString(1, Database.getPrimaryKeyString(pkValue, "IS", 8));
            prepSql.setString(2, item.getValue().getItemDate().toString());
            prepSql.setString(3, item.getValue().getItem());
            prepSql.setString(4, item.getValue().getItemActualAmount().toString());
            prepSql.setString(5, Double.toString(col3.getCellData(5)));
            System.out.println(prepSql);
            rowInserted = prepSql.executeUpdate();

            if (rowInserted > 0) {
                System.out.println("Inserted sql: " + prepSql);
            } else {
                System.out.println("failed: ");
            }
        }

        for (int i = 3; i < items.size(); i++) {
            item = items.get(i);
            pkValue = Database.getPrimaryKeyValue(conn, "income_statement_t");

            prepSql.setString(1, Database.getPrimaryKeyString(pkValue + i, "IS", 8));
            prepSql.setString(2, item.getValue().getItemDate().toString());
            prepSql.setString(3, item.getValue().getItem());
            prepSql.setString(4, item.getValue().getItemActualAmount().toString());
            prepSql.setString(5, Double.toString(col3.getCellData(i + 4)));

            System.out.println(prepSql);
            prepSql.addBatch();

            if (i + 1 == items.size()) {
                prepSql.executeBatch();
            }
        }
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
