package com.carte.clouds5spring.dto;

import java.math.BigDecimal;

public class FirebaseRouteProblemeDTO 
{
    private String firebaseId;
    private BigDecimal surface;
    private BigDecimal budget;
    private String status;
    private String entreprise;

    public FirebaseRouteProblemeDTO() {}

    public FirebaseRouteProblemeDTO(String firebaseId, BigDecimal surface, BigDecimal budget, String status, String entreprise) {
        this.firebaseId = firebaseId;
        this.surface = surface;
        this.budget = budget;
        this.status = status;
        this.entreprise = entreprise;
    }

    public String getFirebaseId() {
        return firebaseId;
    }
    public void setFirebaseId(String firebaseId) {
        this.firebaseId = firebaseId;
    }
    public BigDecimal getSurface() {
        return surface;
    }
    public void setSurface(BigDecimal surface) {
        this.surface = surface;
    }
    public BigDecimal getBudget() {
        return budget;
    }
    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getEntreprise() {
        return entreprise;
    }
    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

}
