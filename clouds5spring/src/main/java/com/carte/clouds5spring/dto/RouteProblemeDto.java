package com.carte.clouds5spring.dto;

import java.math.BigDecimal;

public class RouteProblemeDto {
    private Integer id;
    private BigDecimal surface;
    private BigDecimal budget;
    private Integer routeEntrepriseId;
    private Integer routeStatusId;

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
}
