package com.assignment.sdp_application;

import java.time.LocalDate;

public class Expenses {
    private String expenseName;
    private double expenseAmount;
    private LocalDate expenseDate;

    public Expenses(LocalDate expenseDate) {
        this.expenseDate = expenseDate;
    }

    public Expenses(String expenseName, double expenseAmount, LocalDate expenseDate) {
        this.expenseName = expenseName;
        this.expenseAmount = expenseAmount;
        this.expenseDate = expenseDate;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public double getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(double expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public LocalDate getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(LocalDate expenseDate) {
        this.expenseDate = expenseDate;
    }
}
