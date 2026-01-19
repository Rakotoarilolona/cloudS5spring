package com.carte.clouds5spring.dto;

import java.util.Date;

public class FirebaseUserDTO 
{
    private String uid;
    private String email;
    private String pseudo;
    private Date createdAt;
    private String password;
    private String role;

    public FirebaseUserDTO() {}

    public FirebaseUserDTO(String uid, String email, String pseudo, Date createdAt, String role) {
        this.uid = uid;
        this.email = email;
        this.pseudo = pseudo;
        this.createdAt = createdAt;
        this.role = role;
    }

    public String getUid() {
        return uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPseudo() {
        return pseudo;
    }
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
    public Date getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

}
