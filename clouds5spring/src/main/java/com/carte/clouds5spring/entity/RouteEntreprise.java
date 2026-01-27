package com.carte.clouds5spring.entity;

import java.util.List;

import com.carte.clouds5spring.dto.RouteEntrepriseDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "routeentreprise")
public class RouteEntreprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "label", length = 50)
    private String label;

    @OneToMany(mappedBy = "routeEntreprise", fetch = FetchType.LAZY)
    private List<RouteProbleme> routeProblemes;

    public RouteEntreprise() {}

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

    public List<RouteProbleme> getRouteProblemes() {
        return routeProblemes;
    }

    public void setRouteProblemes(List<RouteProbleme> routeProblemes) {
        this.routeProblemes = routeProblemes;
    }

    public RouteEntrepriseDto toDto() {
        if (this == null) {
            return null;
        }
        
        RouteEntrepriseDto dto = new RouteEntrepriseDto();
        if(this.getId() != null) {
            dto.setId(this.getId());
        }
        if(this.getLabel() != null) {
            dto.setLabel(this.getLabel());
        }        
        return dto;
    }

}
