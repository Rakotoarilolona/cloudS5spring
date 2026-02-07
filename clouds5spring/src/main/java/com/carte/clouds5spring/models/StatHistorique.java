package com.carte.clouds5spring.models;

import java.util.List;

import com.carte.clouds5spring.dto.HistoriqueStatusRouteDto;
import com.carte.clouds5spring.dto.RouteStatusDto;

public class StatHistorique {
    private RouteStatusDto status;
    private List<HistoriqueStatusRouteDto> problemeList;
    private Double delaisMoyen;

    public StatHistorique() {
    }
    public StatHistorique(RouteStatusDto status, List<HistoriqueStatusRouteDto> problemeList, Double delaisMoyen) {
        this.status = status;
        this.problemeList = problemeList;
        this.delaisMoyen = delaisMoyen;
    }

    public RouteStatusDto getStatus() {
        return status;
    }

    public void setStatus(RouteStatusDto status) {
        this.status = status;
    }

    public List<HistoriqueStatusRouteDto> getProblemeList() {
        return problemeList;
    }

    public void setProblemeList(List<HistoriqueStatusRouteDto> problemeList) {
        this.problemeList = problemeList;
    }

    public Double getDelaisMoyen() {
        return delaisMoyen;
    }

    public void setDelaisMoyen(Double delaisMoyen) {
        this.delaisMoyen = delaisMoyen;
    }

}
