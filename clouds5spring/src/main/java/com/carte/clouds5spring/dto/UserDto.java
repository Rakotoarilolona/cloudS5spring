package com.carte.clouds5spring.dto;

import com.carte.clouds5spring.entity.User;
public class UserDto {
    private Integer id;
    private String email;
    private String nom;
    private String prenom;
    private Integer nbrTentative;
    private Integer userRoleId;

    public UserDto() {}

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

    public Integer getNbrTentative() {
        return nbrTentative;
    }

    public void setNbrTentative(Integer nbrTentative) {
        this.nbrTentative = nbrTentative;
    }

    public Integer getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }
    public User toEntity() {
        if (this == null) {
            return null;
        }
        
        User entity = new User();
        if(this.getId() != null) {
            entity.setId(this.getId());
        }
        if(this.getEmail() != null) {
            entity.setEmail(this.getEmail());
        }
        if(this.getNom() != null) {
            entity.setNom(this.getNom());
        }
        if(this.getPrenom() != null) {
            entity.setPrenom(this.getPrenom());
        }
        if(this.getNbrTentative() != null) {
            entity.setNbrTentative(this.getNbrTentative());
        }
        return entity;
    }
}
