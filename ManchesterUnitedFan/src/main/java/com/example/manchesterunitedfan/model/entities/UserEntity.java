package com.example.manchesterunitedfan.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity{
    private String username;
    private String password;
    private String email;
    private String imgUrl;
    private BigDecimal accountBalance;
    private List<ProductEntity> ownedItems = new ArrayList<>();
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

    @Column(name = "account_balance", nullable = false)
    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public UserEntity setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
        return this;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    public List<ProductEntity> getOwnedItems() {
        return ownedItems;
    }

    public UserEntity setOwnedItems(List<ProductEntity> ownedItems) {
        this.ownedItems = ownedItems;
        return this;
    }
}
