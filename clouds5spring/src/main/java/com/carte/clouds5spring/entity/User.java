package com.carte.clouds5spring.entity;

import jakarta.persistence.*;
import java.util.List;

import com.carte.clouds5spring.dto.UserDto;

@Entity
@Table(name = "user_")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "nom", length = 100)
    private String nom;

    @Column(name = "prenom", length = 100)
    private String prenom;

    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "nbrTentative")
    private Integer nbrTentative;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_userRole")
    private UserRole userRole;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserTentativeHistorique> tentatives;

    public User() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getNbrTentative() {
        return nbrTentative;
    }

    public void setNbrTentative(Integer nbrTentative) {
        this.nbrTentative = nbrTentative;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public List<UserTentativeHistorique> getTentatives() {
        return tentatives;
    }

    public void setTentatives(List<UserTentativeHistorique> tentatives) {
        this.tentatives = tentatives;
    }
    public UserDto toDto() {
        if (this == null) {
            return null;
        }
        
        UserDto dto = new UserDto();
        if(this.getId() != null) {
            dto.setId(this.getId());
        }
        if(this.getEmail() != null) {
            dto.setEmail(this.getEmail());
        }
        if(this.getNom() != null) {
            dto.setNom(this.getNom());
        }
        if(this.getPrenom() != null) {
            dto.setPrenom(this.getPrenom());
        }
        if(this.getNbrTentative() != null) {
            dto.setNbrTentative(this.getNbrTentative());
        }
        if(this.getUserRole() != null && this.getUserRole().getId() != null) {
            dto.setUserRoleId(this.getUserRole().getId());
        }
        return dto;
    }
}
