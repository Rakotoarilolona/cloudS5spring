package com.carte.clouds5spring.dto;

import java.math.BigDecimal;


public class FirebaseRouteProblemeDTO 
{
    private String firebaseId;
    private BigDecimal surface;
    private BigDecimal budget;
    private String status;
    private String idStatus;
    private String entreprise;
    private String idEntreprise;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String description;


    // Constructeur par défaut
    public FirebaseRouteProblemeDTO() {
    }

    // Constructeur avec tous les paramètres
    public FirebaseRouteProblemeDTO(String firebaseId, BigDecimal surface, BigDecimal budget, 
                                   String status, String idStatus, String entreprise, String descri,
                                   String idEntreprise, BigDecimal latitude, BigDecimal longitude) {
        this.firebaseId = firebaseId;
        this.surface = surface;
        this.budget = budget;
        this.status = status;
        this.description = descri;
        this.idStatus = idStatus;
        this.entreprise = entreprise;
        this.idEntreprise = idEntreprise;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    // Getters et Setters
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

    public String getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(String idStatus) {
        this.idStatus = idStatus;
    }

    public String getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    public String getIdEntreprise() {
        return idEntreprise;
    }

    public void setIdEntreprise(String idEntreprise) {
        this.idEntreprise = idEntreprise;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "FirebaseRouteProblemeDTO{" +
                "firebaseId='" + firebaseId + '\'' +
                ", surface=" + surface +
                ", budget=" + budget +
                ", status='" + status + '\'' +
                ", idStatus='" + idStatus + '\'' +
                ", entreprise='" + entreprise + '\'' +
                ", idEntreprise='" + idEntreprise + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}