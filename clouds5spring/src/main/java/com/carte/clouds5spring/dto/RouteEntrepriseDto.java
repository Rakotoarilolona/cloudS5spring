package com.carte.clouds5spring.dto;

import com.carte.clouds5spring.entity.RouteEntreprise;

public class RouteEntrepriseDto {
    private Integer id;
    private String label;

    public RouteEntrepriseDto() {}

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
    public RouteEntreprise toEntity() {
        if (this == null) {
            return null;
        }
        
        RouteEntreprise entity = new RouteEntreprise();
        if(this.getId() != null) {
            entity.setId(this.getId());
        }
        if(this.getLabel() != null) {
            entity.setLabel(this.getLabel());
        }
        return entity;
    }
}
