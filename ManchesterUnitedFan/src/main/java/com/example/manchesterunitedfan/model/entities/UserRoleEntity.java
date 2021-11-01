package com.example.manchesterunitedfan.model.entities;

import com.example.manchesterunitedfan.model.enums.UserRoleEnum;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class UserRoleEntity extends BaseEntity {
    private UserRoleEnum name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public UserRoleEnum getName() {
        return name;
    }

    public UserRoleEntity setName(UserRoleEnum name) {
        this.name = name;
        return this;
    }
}
