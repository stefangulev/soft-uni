package com.example.pathfinder.model;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity{
    private RoleEnum role;

    @Enumerated(EnumType.STRING)
    public RoleEnum getRole() {
        return role;
    }

    public Role setRole(RoleEnum role) {
        this.role = role;
        return this;
    }
}
