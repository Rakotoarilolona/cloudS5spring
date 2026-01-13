package com.carte.clouds5spring.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "userRole")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "label", length = 50)
    private String label;

    @OneToMany(mappedBy = "userRole", fetch = FetchType.LAZY)
    private List<User> users;

    public UserRole() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
