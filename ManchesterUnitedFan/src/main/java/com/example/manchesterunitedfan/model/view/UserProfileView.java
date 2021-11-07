package com.example.manchesterunitedfan.model.view;

import com.example.manchesterunitedfan.model.entities.UserRoleEntity;

import java.util.HashSet;
import java.util.Set;

public class UserProfileView {
    private String username;
    private String password;
    private String email;
    private String imgUrl;
    private Set<UserRoleEntity> role = new HashSet<>();

    public String getUsername() {
        return username;
    }

    public UserProfileView setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserProfileView setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserProfileView setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public UserProfileView setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public Set<UserRoleEntity> getRole() {
        return role;
    }

    public UserProfileView setRole(Set<UserRoleEntity> role) {
        this.role = role;
        return this;
    }
}
