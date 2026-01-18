package com.carte.clouds5spring.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import com.carte.clouds5spring.dto.UserTentativeHistoriqueDto;

@Entity
@Table(name = "userTentativeHistorique")
public class UserTentativeHistorique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "dateHistorique")
    private LocalDateTime dateHistorique;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;

    public UserTentativeHistorique() {}

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserTentativeHistoriqueDto toDto() {
    if (this == null) {
        return null;
    }
    
    UserTentativeHistoriqueDto dto = new UserTentativeHistoriqueDto();
        if(this.getId() != null) {
            dto.setId(this.getId());
        }
        if(this.getDateHistorique() != null) {
            dto.setDateHistorique(this.getDateHistorique());
        }
        if(this.getUser() != null && this.getUser().getId() != null) {
            dto.setUserId(this.getUser().getId());
        }
        return dto;
    }
}
