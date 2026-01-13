package com.carte.clouds5spring.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "routeEntreprise")
public class RouteEntreprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "label", length = 50)
    private String label;

    @OneToMany(mappedBy = "routeEntreprise", fetch = FetchType.LAZY)
    private List<RouteProbleme> routeProblemes;

    public RouteEntreprise() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<RouteProbleme> getRouteProblemes() {
        return routeProblemes;
    }

    public void setRouteProblemes(List<RouteProbleme> routeProblemes) {
        this.routeProblemes = routeProblemes;
    }
}
