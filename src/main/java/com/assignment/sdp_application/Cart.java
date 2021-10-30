package com.assignment.sdp_application;

public class Cart{
    String productName;
    int quantity;
    double price;
    double oriprice;


    public Cart(String productName, int quantity, double price, double oriprice) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.oriprice = oriprice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getOriprice() {
        return oriprice;
    }

    public void setOriprice(double oriprice) {
        this.oriprice = oriprice;
    }
}
