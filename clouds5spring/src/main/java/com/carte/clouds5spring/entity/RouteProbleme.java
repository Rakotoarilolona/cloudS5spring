package com.carte.clouds5spring.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

import com.carte.clouds5spring.dto.RouteProblemeDto;

@Entity
@Table(name = "routeProbleme")
public class RouteProbleme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "surface", precision = 15, scale = 2)
    private BigDecimal surface;

    @Column(name = "budget", precision = 15, scale = 2)
    private BigDecimal budget;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_routeEntreprise")
    private RouteEntreprise routeEntreprise;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_routeStatus")
    private RouteStatus routeStatus;

    public RouteProbleme() {}

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

    public RouteEntreprise getRouteEntreprise() {
        return routeEntreprise;
    }

    public void setRouteEntreprise(RouteEntreprise routeEntreprise) {
        this.routeEntreprise = routeEntreprise;
    }

    public RouteStatus getRouteStatus() {
        return routeStatus;
    }

    public void setRouteStatus(RouteStatus routeStatus) {
        this.routeStatus = routeStatus;
    }
    public RouteProblemeDto toDto() {
    if (this == null) {
        return null;
    }
    
    RouteProblemeDto dto = new RouteProblemeDto();
        if(this.getId() != null) {
            dto.setId(this.getId());
        }
        if(this.getSurface() != null) {
            dto.setSurface(this.getSurface());
        }
        if(this.getBudget() != null) {
            dto.setBudget(this.getBudget());
        }
        if(this.getRouteEntreprise() != null && this.getRouteEntreprise().getId() != null) {
            dto.setRouteEntrepriseId(this.getRouteEntreprise().getId());
        }
        if(this.getRouteStatus() != null && this.getRouteStatus().getId() != null) {
            dto.setRouteStatusId(this.getRouteStatus().getId());
        }
        return dto;
    }
}
