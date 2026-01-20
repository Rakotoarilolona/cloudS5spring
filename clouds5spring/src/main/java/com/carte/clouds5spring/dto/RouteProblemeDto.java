package com.carte.clouds5spring.dto;

import java.math.BigDecimal;

import com.carte.clouds5spring.entity.RouteProbleme;

public class RouteProblemeDto {
    private Integer id;
    private BigDecimal surface;
    private BigDecimal budget;
    private Integer routeEntrepriseId;
    private String routeEntrepriseName;
    private Integer routeStatusId;
    private String routeStatusName;
    private String problemeDescription;
    private BigDecimal longitude;
    private BigDecimal latitude;


    public String getRouteEntrepriseName() {
        return routeEntrepriseName;
    }

    public void setRouteEntrepriseName(String routeEntrepriseName) {
        this.routeEntrepriseName = routeEntrepriseName;
    }

    public String getRouteStatusName() {
        return routeStatusName;
    }

    public void setRouteStatusName(String routeStatusName) {
        this.routeStatusName = routeStatusName;
    }

    public String getProblemeDescription() {
        return problemeDescription;
    }

    public void setProblemeDescription(String problemeDescription) {
        this.problemeDescription = problemeDescription;
    }


    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public RouteProblemeDto() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getRouteEntrepriseId() {
        return routeEntrepriseId;
    }

    public void setRouteEntrepriseId(Integer routeEntrepriseId) {
        this.routeEntrepriseId = routeEntrepriseId;
    }

    public Integer getRouteStatusId() {
        return routeStatusId;
    }

    public void setRouteStatusId(Integer routeStatusId) {
        this.routeStatusId = routeStatusId;
    }
    public RouteProbleme toEntity() {
        if (this == null) {
            return null;
        }
        
        RouteProbleme entity = new RouteProbleme();
        if(this.getId() != null) {
            entity.setId(this.getId());
        }
        if(this.getSurface() != null) {
            entity.setSurface(this.getSurface());
        }
        if(this.getBudget() != null) {
            entity.setBudget(this.getBudget());
        }
        if(this.getLongitude() != null) {
            entity.setLongitude(this.getLongitude());
        }
        if(this.getLatitude() != null) {
            entity.setLatitude(this.getLatitude());
        }
        if(this.getProblemeDescription() != null) {
            entity.setProblemeDescription(this.getProblemeDescription());
        }
        return entity;
    }
}
