package com.carte.clouds5spring.dto;

import com.carte.clouds5spring.entity.RouteStatus;

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

    public RouteStatus toEntity() {
        if (this == null) {
            return null;
        }
        
        RouteStatus entity = new RouteStatus();
        if(this.getId() != null) {
            entity.setId(this.getId());
        }
        if(this.getLabel() != null) {
            entity.setLabel(this.getLabel());
        }
        if(this.getValeur() != null) {
            entity.setValeur(this.getValeur());
        }
        return entity;
    }
}
