package com.carte.clouds5spring.dto;

public class UserUpdateRequest {

    private String email;
    private String password; // optionnel
    private Integer roleId;  // ADMIN uniquement

    // getters & setters
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
    public Integer getRoleId() {
        return roleId;
    }
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
