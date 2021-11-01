package com.assignment.sdp_application;

import javafx.scene.control.TreeItem;

import java.time.LocalDate;
import java.util.ArrayList;

public class BudgetingReport {

    private ArrayList<TreeItem<IncomeStatement>> incomestatement_items;
    private LocalDate incomestatement_dates;

    public BudgetingReport(LocalDate incomestatement_dates) {
        this.incomestatement_dates = incomestatement_dates;
    }

    public BudgetingReport(ArrayList<TreeItem<IncomeStatement>> incomestatement_items, LocalDate incomestatement_dates) {
        this.incomestatement_items = incomestatement_items;
        this.incomestatement_dates = incomestatement_dates;
    }

    public ArrayList<TreeItem<IncomeStatement>> getIncomestatement_items() {
        return incomestatement_items;
    }

    public void setIncomestatement_items(ArrayList<TreeItem<IncomeStatement>> incomestatement_items) {
        this.incomestatement_items = incomestatement_items;
    }

    public LocalDate getIncomestatement_dates() {
        return incomestatement_dates;
    }

    public void setIncomestatement_dates(LocalDate incomestatement_dates) {
        this.incomestatement_dates = incomestatement_dates;
    }
}
