package com.example.manchesterunitedfan.model.view;

import com.example.manchesterunitedfan.model.entities.UserRoleEntity;

import java.util.HashSet;
import java.util.Set;

public class UserAdminPageView {
    private Long id;
    private String username;
    private Set<UserRoleEntity> role = new HashSet<>();

    public Long getId() {
        return id;
    }

    public UserAdminPageView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserAdminPageView setUsername(String username) {
        this.username = username;
        return this;
    }

    public Set<UserRoleEntity> getRole() {
        return role;
    }

    public UserAdminPageView setRole(Set<UserRoleEntity> role) {
        this.role = role;
        return this;
    }
}
