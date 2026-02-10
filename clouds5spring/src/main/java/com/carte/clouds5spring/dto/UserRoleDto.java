package com.carte.clouds5spring.dto;
import com.carte.clouds5spring.entity.UserRole;
public class UserRoleDto {
    private Integer id;
    private String label;

    public UserRoleDto() {}

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
}
