package com.carte.clouds5spring.dto;

import java.time.LocalDateTime;


public class HistoriqueStatusRouteDto {

    private Integer id;
    private LocalDateTime dateHistorique;

    // Infos du signalement
    private RouteProblemeDto routeProbleme;

    // Infos du statut
    private Integer routeStatusId;
    private String routeStatusLabel;

    public HistoriqueStatusRouteDto() {}

    public HistoriqueStatusRouteDto(Integer id,
                                    LocalDateTime dateHistorique,
                                    RouteProblemeDto routeProbleme,
                                    Integer routeStatusId,
                                    String routeStatusLabel) {
        this.id = id;
        this.dateHistorique = dateHistorique;
        this.routeProbleme = routeProbleme;
        this.routeStatusId = routeStatusId;
        this.routeStatusLabel = routeStatusLabel;
    }

    // Getters & Setters
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

    public RouteProblemeDto getRouteProbleme() {
        return routeProbleme;
    }

    public void setRouteProbleme(RouteProblemeDto routeProbleme) {
        this.routeProbleme = routeProbleme;
    }

    public Integer getRouteStatusId() {
        return routeStatusId;
    }

    public void setRouteStatusId(Integer routeStatusId) {
        this.routeStatusId = routeStatusId;
    }

    public String getRouteStatusLabel() {
        return routeStatusLabel;
    }

    public void setRouteStatusLabel(String routeStatusLabel) {
        this.routeStatusLabel = routeStatusLabel;
    }
}
