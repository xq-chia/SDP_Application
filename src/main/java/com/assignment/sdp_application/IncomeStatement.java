package com.assignment.sdp_application;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.YearMonth;

public class IncomeStatement {
    String item;
    Double itemBudgetedAmount;
    Double itemActualAmount;
    LocalDate itemDate;

    public IncomeStatement(String item) {
        this.item = item;
    }

    public IncomeStatement(String item, Double itemBudgetedAmount, Double itemActualAmount, LocalDate itemDate) {
        this.item = item;
        this.itemBudgetedAmount = itemBudgetedAmount;
        this.itemActualAmount = itemActualAmount;
        this.itemDate = itemDate;
    }


    public IncomeStatement(String item, Double itemActualAmount, LocalDate itemDate) {
        this.item = item;
        this.itemActualAmount = itemActualAmount;
        this.itemDate = itemDate;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Double getItemBudgetedAmount() {
        return itemBudgetedAmount;
    }

    public void setItemBudgetedAmount(Double itemBudgetedAmount) {
        this.itemBudgetedAmount = itemBudgetedAmount;
    }

    public Double getItemActualAmount() {
        return itemActualAmount;
    }

    public void setItemActualAmount(Double itemActualAmount) {
        this.itemActualAmount = itemActualAmount;
    }

    public LocalDate getItemDate() {
        return itemDate;
    }

    public void setItemDate(LocalDate itemDate) {
        this.itemDate = itemDate;
    }
}
