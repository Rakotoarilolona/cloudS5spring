package com.carte.clouds5spring.models;
import java.util.List;

import com.carte.clouds5spring.dto.*;
import com.carte.clouds5spring.entity.*;
public class RouteDashboard {
    private int totalProblemes;
    private double avancement;
    private double totalSurface;
    private double totalBudget;
    public RouteDashboard(int totalProblemes, double avancement, double totalSurface, double totalBudget) {
        this.totalProblemes = totalProblemes;
        this.avancement = avancement;
        this.totalSurface = totalSurface;
        this.totalBudget = totalBudget;
    }
    public int getTotalProblemes() {
        return totalProblemes;
    }
    public void setTotalProblemes(int totalProblemes) {
        this.totalProblemes = totalProblemes;
    }
    public double getAvancement() {
        return avancement;
    }
    public void setAvancement(double avancement) {
        this.avancement = avancement;
    }
    public double getTotalSurface() {
        return totalSurface;
    }
    public void setTotalSurface(double totalSurface) {
        this.totalSurface = totalSurface;
    }
    public double getTotalBudget() {
        return totalBudget;
    }
    public void setTotalBudget(double totalBudget) {
        this.totalBudget = totalBudget;
    }
    public static RouteDashboard calcul(List<RouteProblemeDto> problemeList)
    {
        if(problemeList == null || problemeList.isEmpty()) {
            return new RouteDashboard(0, 0.0, 0.0, 0.0);
        }
        // Logique de calcul des indicateurs du tableau de bord
        int totalProblemes = 150; // Exemple de valeur
        double avancement = 75.5; // Exemple de valeur en pourcentage
        double totalSurface = 1200.0; // Exemple de valeur en km²
        double totalBudget = 500000.0; // Exemple de valeur en euros

        totalProblemes = problemeList.size();
        double sommeSurface = 0.0;
        double sommeBudget = 0.0;
        for (RouteProblemeDto probleme : problemeList) {
            sommeSurface += probleme.getSurface() != null ? probleme.getSurface().doubleValue() : 0.0;
            sommeBudget += probleme.getBudget() != null ? probleme.getBudget().doubleValue() : 0.0;
        }   
        return new RouteDashboard(totalProblemes, avancement, sommeSurface, sommeBudget);   
    }

    
    
}
