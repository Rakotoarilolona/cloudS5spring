package com.carte.clouds5spring.models;
import java.util.List;

import java.util.Map;
import java.util.HashMap;
import com.carte.clouds5spring.dto.*;
import com.carte.clouds5spring.entity.*;

public class RouteDashboard {
    private List<StatHistorique> statistiques;
    private double tauxReussite;
    private double delaisMoyenGlobal;

    public RouteDashboard(List<StatHistorique> statistiques, double tauxReussite, double delaisMoyenGlobal) {
        this.statistiques = statistiques;
        this.tauxReussite = tauxReussite;
        this.delaisMoyenGlobal = delaisMoyenGlobal;
    }
    public List<StatHistorique> getStatistiques() {
        return statistiques;
    }
    public void setStatistiques(List<StatHistorique> statistiques) {
        this.statistiques = statistiques;
    }
    public double getTauxReussite() {
        return tauxReussite;
    }
    public void setTauxReussite(double tauxReussite) {
        this.tauxReussite = tauxReussite;
    }
    public double getDelaisMoyenGlobal() {
        return delaisMoyenGlobal;
    }
    public void setDelaisMoyenGlobal(double delaisMoyenGlobal) {
        this.delaisMoyenGlobal = delaisMoyenGlobal;
    }
    

}
