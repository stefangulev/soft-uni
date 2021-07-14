package com.example.demo.models.entities;

import org.hibernate.annotations.Cache;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    private String email;
    private String password;
    private String fullName;
    private Set<Game> gameSet;
    private boolean isAdministrator;

    public User() {
        gameSet = new HashSet<>();
    }

    public User(String email, String password, String fullName, boolean isAdministrator) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.gameSet = new LinkedHashSet<>();
        this.isAdministrator = isAdministrator;
    }

    @Column
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    @ManyToMany
    public Set<Game> getGameSet() {
        return gameSet;
    }

    public void setGameSet(Set<Game> gamesList) {
        this.gameSet = gamesList;
    }

    @Column
    public boolean isAdministrator() {
        return isAdministrator;
    }

    public void setAdministrator(boolean administrator) {
        isAdministrator = administrator;
    }
}
