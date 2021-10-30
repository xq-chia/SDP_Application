package com.assignment.sdp_application;

public class Purchases {
    private String purchaseId;
    private String productName;
    private String supplierName;
    private double price;
    private int quantity;
    private double totalcost;
    private double discount;

    public Purchases(String purchaseId, String productName, String supplierName, double price, int quantity, double totalcost, double discount) {
        this.purchaseId = purchaseId;
        this.productName = productName;
        this.supplierName = supplierName;
        this.price = price;
        this.quantity = quantity;
        this.totalcost = totalcost;
        this.discount = discount;
    }

    public String getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalcost() {
        return totalcost;
    }

    public void setTotalcost(double totalcost) {
        this.totalcost = totalcost;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
