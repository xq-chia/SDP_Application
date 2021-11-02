package com.assignment.sdp_application;

import javafx.scene.control.TreeItem;

import java.time.Year;
import java.time.YearMonth;
import java.time.YearMonth;
import java.util.ArrayList;

public class BudgetingReport {

    private ArrayList<TreeItem<IncomeStatement>> incomestatement_items;
    private int year;
    private String months;

    public BudgetingReport(int year) {
        this.year = year;
    }

    public BudgetingReport(String months) {
        this.months = months;
    }
    public BudgetingReport(ArrayList<TreeItem<IncomeStatement>> incomestatement_items, int year, String months) {
        this.incomestatement_items = incomestatement_items;
        this.year = year;
        this.months = months;
    }


    public BudgetingReport() {

    }

    public ArrayList<TreeItem<IncomeStatement>> getIncomestatement_items() {
        return incomestatement_items;
    }

    public void setIncomestatement_items(ArrayList<TreeItem<IncomeStatement>> incomestatement_items) {
        this.incomestatement_items = incomestatement_items;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMonths() {
        return months;
    }

    public void setMonths(String months) {
        this.months = months;
    }
}
