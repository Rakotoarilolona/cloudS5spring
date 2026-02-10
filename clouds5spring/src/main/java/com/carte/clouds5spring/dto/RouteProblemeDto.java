package com.carte.clouds5spring.dto;

import java.math.BigDecimal;
import java.util.List;

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
    private Integer niveau;
    private RouteEntrepriseDto entreprise;
    private List<PhotoDto> photos;

    public RouteProblemeDto(
            Integer id,
            BigDecimal surface,
            BigDecimal budget,
            Integer routeEntrepriseId,
            String routeEntrepriseName,
            Integer routeStatusId,
            String routeStatusName,
            String problemeDescription,
            BigDecimal longitude,
            BigDecimal latitude,
            Integer niveau
    ) {
        this.id = id;
        this.surface = surface;
        this.budget = budget;
        this.routeEntrepriseId = routeEntrepriseId;
        this.routeEntrepriseName = routeEntrepriseName;
        this.routeStatusId = routeStatusId;
        this.routeStatusName = routeStatusName;
        this.problemeDescription = problemeDescription;
        this.longitude = longitude;
        this.latitude = latitude;
        this.niveau = niveau;
    }

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

    public Integer getNiveau() {
        return niveau;
    }

    public void setNiveau(Integer niveau) {
        this.niveau = niveau;
    }

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

    public RouteEntrepriseDto getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(RouteEntrepriseDto entreprise) {
        this.entreprise = entreprise;
    }

    public List<PhotoDto> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotoDto> photos) {
        this.photos = photos;
    }
    public RouteProbleme toEntity() {
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
        if (this.getNiveau() != null) {
            entity.setNiveau(this.getNiveau());
        }
        return entity;
    }
}
