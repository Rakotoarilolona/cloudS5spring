package com.carte.clouds5spring.dto;

import java.math.BigDecimal;

public class AssignEntrepriseDto 
{
    private Integer entrepriseId;
    private BigDecimal budget;
    private Integer niveau;
    
    public Integer getEntrepriseId() {
        return entrepriseId;
    }
    public void setEntrepriseId(Integer entrepriseId) {
        this.entrepriseId = entrepriseId;
    }
    public BigDecimal getBudget() {
        return budget;
    }
    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public Integer getNiveau() {
        return niveau;
    }

    public void setNiveau(Integer niveau) {
        this.niveau = niveau;
    }
}
