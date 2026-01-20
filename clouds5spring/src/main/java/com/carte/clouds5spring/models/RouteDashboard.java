package com.carte.clouds5spring.models;
import java.util.List;

import java.util.Map;
import java.util.HashMap;
import com.carte.clouds5spring.dto.*;
import com.carte.clouds5spring.entity.*;

public class RouteDashboard {
    private int totalProblemes;
    private Map<String, Double> avancement;
    private double totalSurface;
    private double totalBudget;
    public RouteDashboard(int totalProblemes, Map<String, Double> avancement, double totalSurface, double totalBudget) {
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
    public Map<String, Double> getAvancement() {
        return avancement;
    }
    public void setAvancement(Map<String, Double> avancement) {
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
    public static Map<String,Double> calculateAvancement(List<RouteProblemeDto> problemeList,List<RouteStatusDto> statusList)
    {
        Map<String,Double> avancementMap =new HashMap<>();
        for(int i=0;i<statusList.size();i++)
        {
            double pourcentage=0;
            avancementMap.put(statusList.get(i).getLabel(), 0.0);
            for(int j=0 ; j< problemeList.size(); j++)
            {
                if(problemeList.get(j).getRouteStatusId()!=null && problemeList.get(j).getRouteStatusId().equals(statusList.get(i).getId()))
                {
                    pourcentage++;
                }
            }
            if(problemeList.size()>0)
            {
                pourcentage=(pourcentage/problemeList.size())*100;
            }
            avancementMap.put(statusList.get(i).getLabel(), pourcentage);
        }
        return avancementMap;
    }
    public static RouteDashboard calcul(List<RouteProblemeDto> problemeList,List<RouteStatusDto> statusList)
    {
        if(problemeList == null || problemeList.isEmpty()) {
            return new RouteDashboard(0, null, 0.0, 0.0);
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
        
        return new RouteDashboard(totalProblemes, calculateAvancement(problemeList, statusList), sommeSurface, sommeBudget);   
    }

    
    
}
