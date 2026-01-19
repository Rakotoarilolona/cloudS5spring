package com.carte.clouds5spring.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "historiqueStatusRoute")
public class HistoriqueStatusRoute 
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "dateHistorique", nullable = false)
    private LocalDateTime dateHistorique;

    @ManyToOne
    @JoinColumn(name = "id_routeProbleme", nullable = false)
    private RouteProbleme routeProbleme;

    @ManyToOne
    @JoinColumn(name = "id_routeStatus", nullable = false)
    private RouteStatus routeStatus;

    public HistoriqueStatusRoute() {
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public LocalDateTime getDateHistorique() {
        return dateHistorique;
    }
    public void setDateHistorique(LocalDateTime dateHistorique) {
        this.dateHistorique = dateHistorique;
    }
    public RouteProbleme getRouteProbleme() {
        return routeProbleme;
    }
    public void setRouteProbleme(RouteProbleme routeProbleme) {
        this.routeProbleme = routeProbleme;
    }
    public RouteStatus getRouteStatus() {
        return routeStatus;
    }
    public void setRouteStatus(RouteStatus routeStatus) {
        this.routeStatus = routeStatus;
    }

}
