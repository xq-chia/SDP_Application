package com.assignment.sdp_application;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Sales{
    private String salesId;
    private String productName;
    private int salesQuantity;
    private double salesAmount;
    private LocalDateTime salesDate;

    public Sales(String salesId, String productName, int salesQuantity, double salesAmount, LocalDateTime salesDate) {
        this.salesId = salesId;
        this.productName = productName;
        this.salesQuantity = salesQuantity;
        this.salesAmount = salesAmount;
        this.salesDate = salesDate;
    }

    public String getSalesId() {
        return salesId;
    }

    public void setSalesId(String salesId) {
        this.salesId = salesId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getSalesQuantity() {
        return salesQuantity;
    }

    public void setSalesQuantity(int salesQuantity) {
        this.salesQuantity = salesQuantity;
    }

    public double getSalesAmount() {
        return salesAmount;
    }

    public void setSalesAmount(double salesAmount) {
        this.salesAmount = salesAmount;
    }

    public LocalDateTime getSalesDate() {
        return salesDate;
    }

    public void setSalesDate(LocalDateTime salesDate) {
        this.salesDate = salesDate;
    }
}
