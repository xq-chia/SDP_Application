package com.assignment.sdp_application;

public class Product {

    private String productId;
    private String supplierName;
    private String productName;
    private double productPrice;
    private boolean productStatus;
    private String productType;
    private int productQuantity;

    public Product(String productId, String supplierName, String productName, double productPrice, boolean productStatus, String productType, int productQuantity) {
        this.productId = productId;
        this.supplierName = supplierName;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStatus = productStatus;
        this.productType = productType;
        this.productQuantity = productQuantity;
    }

    public String getProductId() {
        return productId;

    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public boolean isProductStatus() {
        return productStatus;
    }

    public void setProductStatus(boolean productStatus) {
        this.productStatus = productStatus;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }
}
