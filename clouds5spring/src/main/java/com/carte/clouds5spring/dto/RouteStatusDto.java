package com.carte.clouds5spring.dto;

public class RouteStatusDto {
    private Integer id;
    private String label;
    private Integer valeur;

    public RouteStatusDto() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getValeur() {
        return valeur;
    }

    public void setValeur(Integer valeur) {
        this.valeur = valeur;
    }
}
