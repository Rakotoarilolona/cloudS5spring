package com.carte.clouds5spring.models;
import java.util.List;

import java.util.Map;
import java.util.HashMap;
import com.carte.clouds5spring.dto.*;
import com.carte.clouds5spring.entity.*;

public class StatHistorique {
    private RouteStatusDto status;
    private List<HistoriqueStatusRoute> problemeList;
    private Double delaisMoyen;

    public StatHistorique() {
    }
    public StatHistorique(RouteStatusDto status, List<HistoriqueStatusRoute> problemeList, Double delaisMoyen) {
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

    public List<HistoriqueStatusRoute> getProblemeList() {
        return problemeList;
    }

    public void setProblemeList(List<HistoriqueStatusRoute> problemeList) {
        this.problemeList = problemeList;
    }

    public Double getDelaisMoyen() {
        return delaisMoyen;
    }

    public void setDelaisMoyen(Double delaisMoyen) {
        this.delaisMoyen = delaisMoyen;
    }

}
