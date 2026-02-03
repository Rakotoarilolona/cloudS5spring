package com.carte.clouds5spring.models;
import java.util.List;

import java.util.Map;
import java.util.HashMap;
import com.carte.clouds5spring.dto.*;
import com.carte.clouds5spring.entity.*;

public class RouteDashboard {
    private List<StatHistorique> statistiques;
    private Double delaisMoyenGlobal;

    public RouteDashboard() {

    }
    public RouteDashboard(List<StatHistorique> statistiques, Double delaisMoyenGlobal) {
        this.statistiques = statistiques;
        this.delaisMoyenGlobal = delaisMoyenGlobal;
    }
    public List<StatHistorique> getStatistiques() {
        return statistiques;
    }
    public void setStatistiques(List<StatHistorique> statistiques) {
        this.statistiques = statistiques;
    }
    public Double getDelaisMoyenGlobal() {
        return delaisMoyenGlobal;
    }
    public void setDelaisMoyenGlobal(Double delaisMoyenGlobal) {
        this.delaisMoyenGlobal = delaisMoyenGlobal;
    }
    

}
