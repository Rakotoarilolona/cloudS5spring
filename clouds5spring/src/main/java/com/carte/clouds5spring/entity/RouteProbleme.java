package com.carte.clouds5spring.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "routeProbleme")
public class RouteProbleme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}
