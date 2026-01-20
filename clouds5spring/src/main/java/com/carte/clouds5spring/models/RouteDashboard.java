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
    private List<RouteEntrepriseDto> entrepriseList;
    public RouteDashboard(int totalProblemes, Map<String, Double> avancement, double totalSurface, double totalBudget,List<RouteEntrepriseDto> entrepriseList) {
        this.totalProblemes = totalProblemes;
        this.avancement = avancement;
        this.totalSurface = totalSurface;
        this.totalBudget = totalBudget;
        this.entrepriseList = entrepriseList;
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
            avancementMap.put(statusList.get(i).getLabel(), pourcentage);
        }
        return avancementMap;
    }
    public static RouteDashboard calcul(List<RouteProblemeDto> problemeList,List<RouteStatusDto> statusList,List<RouteEntrepriseDto> entrepriseList)
    {
        if(problemeList == null || problemeList.isEmpty()) {
            return new RouteDashboard(0, null, 0.0, 0.0, null);
        }
        // Logique de calcul des indicateurs du tableau de bord

        int totalProblemes = problemeList.size();
        double sommeSurface = 0.0;
        double sommeBudget = 0.0;
        for (RouteProblemeDto probleme : problemeList) {
            sommeSurface += probleme.getSurface() != null ? probleme.getSurface().doubleValue() : 0.0;
            sommeBudget += probleme.getBudget() != null ? probleme.getBudget().doubleValue() : 0.0;
        }
        
        return new RouteDashboard(totalProblemes, calculateAvancement(problemeList, statusList), sommeSurface, sommeBudget, entrepriseList);   
    }

    
    
}
