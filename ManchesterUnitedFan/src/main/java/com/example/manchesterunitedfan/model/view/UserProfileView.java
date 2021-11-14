package com.example.manchesterunitedfan.model.view;

import com.example.manchesterunitedfan.model.entities.ProductEntity;
import com.example.manchesterunitedfan.model.entities.UserRoleEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserProfileView {
    private String username;
    private String password;
    private String email;
    private String imgUrl;
    private Set<UserRoleView> role = new HashSet<>();
    private BigDecimal accountBalance;
    private List<ProductCardView> ownedItems = new ArrayList<>();

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

    public Set<UserRoleView> getRole() {
        return role;
    }

    public UserProfileView setRole(Set<UserRoleView> role) {
        this.role = role;
        return this;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public UserProfileView setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
        return this;
    }

    public List<ProductCardView> getOwnedItems() {
        return ownedItems;
    }

    public UserProfileView setOwnedItems(List<ProductCardView> ownedItems) {
        this.ownedItems = ownedItems;
        return this;
    }
}
