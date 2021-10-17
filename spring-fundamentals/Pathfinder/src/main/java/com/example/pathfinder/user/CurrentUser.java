package com.example.pathfinder.user;

import com.example.pathfinder.model.LevelEnum;
import com.example.pathfinder.model.Role;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.HashSet;
import java.util.Set;
@Component
@SessionScope
public class CurrentUser {
    private Long id;
    private String username;
    private String password;
    private String fullName;
    private Integer age;
    private String email;
    private Set<Role> role = new HashSet<>();
    private LevelEnum level;
    private boolean isLoggedIn;

    public String getUsername() {
        return username;
    }

    public CurrentUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public CurrentUser setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public CurrentUser setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public CurrentUser setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CurrentUser setEmail(String email) {
        this.email = email;
        return this;
    }

    public Set<Role> getRole() {
        return role;
    }

    public CurrentUser setRole(Set<Role> role) {
        this.role = role;
        return this;
    }

    public LevelEnum getLevel() {
        return level;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public CurrentUser setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
        return this;
    }

    public CurrentUser setLevel(LevelEnum level) {
        this.level = level;
        return this;
    }

    public Long getId() {
        return id;
    }

    public CurrentUser setId(Long id) {
        this.id = id;
        return this;
    }

    public void clear() {
        this.setLoggedIn(false)
                .setUsername(null).setPassword(null)
                .setEmail(null).setAge(null).setLevel(null).setRole(new HashSet<>());
    }
}
