package com.carte.clouds5spring.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import com.carte.clouds5spring.dto.UserDto;

@Entity
@Table(name = "user_")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String pseudo;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "nbrTentative")
    private Integer nbrTentative;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "id_userRole")
    // private UserRole userRole;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_userRole")
    private UserRole userRole;


    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserTentativeHistorique> tentatives;

    @Column
    private LocalDateTime blockedAt;

    @Column(name = "firebase_uid", unique = true)
    private String firebaseUid;

    public User() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public LocalDateTime getBlockedAt() {
        return blockedAt;
    }

    public void setBlockedAt(LocalDateTime blockedAt) {
        this.blockedAt = blockedAt;
    }

    public String getFirebaseUid() {
        return firebaseUid;
    }

    public void setFirebaseUid(String firebaseUid) {
        this.firebaseUid = firebaseUid;
    }

    public UserDto toDto() 
    {
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
        if(this.getPseudo() != null) {
            dto.setPseudo(this.getPseudo());
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
