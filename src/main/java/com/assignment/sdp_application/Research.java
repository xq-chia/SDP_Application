package com.assignment.sdp_application;

import java.lang.invoke.LambdaConversionException;
import java.time.LocalDate;

public class Research {
    private String researchId;
    private String researchName;
    private String researchDescription;
    private double researchCost;
    private LocalDate researchDate;

    public Research(String researchId, String researchName, String researchDescription, double researchCost, LocalDate researchDate) {
        this.researchId = researchId;
        this.researchName = researchName;
        this.researchDescription = researchDescription;
        this.researchCost = researchCost;
        this.researchDate = researchDate;
    }

    public String getResearchId() {
        return researchId;
    }

    public void setResearchId(String researchId) {
        this.researchId = researchId;
    }

    public String getResearchName() {
        return researchName;
    }

    public void setResearchName(String researchName) {
        this.researchName = researchName;
    }

    public String getResearchDescription() {
        return researchDescription;
    }

    public void setResearchDescription(String researchDescription) {
        this.researchDescription = researchDescription;
    }

    public double getResearchCost() {
        return researchCost;
    }

    public void setResearchCost(double researchCost) {
        this.researchCost = researchCost;
    }

    public LocalDate getResearchDate() {
        return researchDate;
    }

    public void setResearchDate(LocalDate researchDate) {
        this.researchDate = researchDate;
    }
}
