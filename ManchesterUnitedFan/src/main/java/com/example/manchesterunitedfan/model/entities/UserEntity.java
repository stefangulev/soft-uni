package com.example.manchesterunitedfan.model.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity{
    private String username;
    private String password;
    private String email;
    private String imgUrl;
    private Set<UserRoleEntity> role = new HashSet<>();

    @Column(nullable = false, unique = true)
    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }
    @Column(nullable = false, unique = true)
    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }
    @Column(name = "img_url")
    public String getImgUrl() {
        return imgUrl;
    }

    public UserEntity setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @Column(nullable = false)
    public Set<UserRoleEntity> getRole() {
        return role;
    }

    public UserEntity setRole(Set<UserRoleEntity> role) {
        this.role = role;
        return this;
    }
}
