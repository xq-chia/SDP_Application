package com.assignment.sdp_application;

import java.time.LocalDate;

public class Promotions {
    private String promoId;
    private String promoType;
    private String promoTarget;
    private double promoCost;
    private LocalDate promoDate;

    public String getPromoId() {
        return promoId;
    }

    public void setPromoId(String promoId) {
        this.promoId = promoId;
    }

    public String getPromoType() {
        return promoType;
    }

    public void setPromoType(String promoType) {
        this.promoType = promoType;
    }

    public String getPromoTarget() {
        return promoTarget;
    }

    public void setPromoTarget(String promoTarget) {
        this.promoTarget = promoTarget;
    }

    public double getPromoCost() {
        return promoCost;
    }

    public void setPromoCost(double promoCost) {
        this.promoCost = promoCost;
    }

    public LocalDate getPromoDate() {
        return promoDate;
    }

    public void setPromoDate(LocalDate promoDate) {
        this.promoDate = promoDate;
    }

    public Promotions(String promoId, String promoType, String promoTarget, double promoCost, LocalDate promoDate) {
        this.promoId = promoId;
        this.promoType = promoType;
        this.promoTarget = promoTarget;
        this.promoCost = promoCost;
        this.promoDate = promoDate;


    }
}
