package com.carte.clouds5spring.entity;

import jakarta.persistence.*;
import java.util.List;

import com.carte.clouds5spring.dto.UserRoleDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "userRole")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "label", length = 50)
    private String label;

    @OneToMany(mappedBy = "userRole", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<User> users;

    public UserRole() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
    public UserRoleDto toDto() {
        if (this == null) {
            return null;
        }
        
        UserRoleDto dto = new UserRoleDto();
        if(this.getId() != null) {
            dto.setId(this.getId());
        }
        if(this.getLabel() != null) {
            dto.setLabel(this.getLabel());
        }
        return dto;
    }
}
