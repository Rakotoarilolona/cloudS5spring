package com.carte.clouds5spring.entity;

import jakarta.persistence.*;
import java.util.List;

import com.carte.clouds5spring.dto.RouteStatusDto;

@Entity
@Table(name = "routeStatus")
public class RouteStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "label", length = 50)
    private String label;

    @Column(name = "valeur")
    private Integer valeur;

    @OneToMany(mappedBy = "routeStatus", fetch = FetchType.LAZY)
    private List<RouteProbleme> routeProblemes;

    public RouteStatus() {}

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

    public List<RouteProbleme> getRouteProblemes() {
        return routeProblemes;
    }

    public void setRouteProblemes(List<RouteProbleme> routeProblemes) {
        this.routeProblemes = routeProblemes;
    }
    public RouteStatusDto toDto() {
        if (this == null) {
            return null;
        }
        
        RouteStatusDto dto = new RouteStatusDto();
        if(this.getId() != null) {
            dto.setId(this.getId());
        }
        if(this.getLabel() != null) {
            dto.setLabel(this.getLabel());
        }
        if(this.getValeur() != null) {
            dto.setValeur(this.getValeur());
        }
        return dto;
    }
}
