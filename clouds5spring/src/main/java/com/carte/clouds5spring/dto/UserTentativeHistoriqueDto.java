package com.carte.clouds5spring.dto;

import java.time.LocalDateTime;

import com.carte.clouds5spring.entity.UserTentativeHistorique;

public class UserTentativeHistoriqueDto {
    private Integer id;
    private LocalDateTime dateHistorique;
    private Integer userId;

    public UserTentativeHistoriqueDto() {}

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public UserTentativeHistorique toEntity() {
    if (this == null) {
        return null;
    }
    
    UserTentativeHistorique entity = new UserTentativeHistorique();
        if(this.getId() != null) {
            entity.setId(this.getId());
        }
        if(this.getDateHistorique() != null) {
            entity.setDateHistorique(this.getDateHistorique());
        }
        return entity;
    }
}
