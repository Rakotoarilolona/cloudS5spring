package com.carte.clouds5spring.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "userTentativeHistorique")
public class UserTentativeHistorique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dateHistorique")
    private LocalDateTime dateHistorique;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;

    public UserTentativeHistorique() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}
