package com.carte.clouds5spring.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

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

    private String firebaseId;

    private LocalDateTime updatedAt;

    // Lié a un user
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;

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

    public String getFirebaseId() {
        return firebaseId;
    }
    public void setFirebaseId(String firebaseId) {
        this.firebaseId = firebaseId;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
