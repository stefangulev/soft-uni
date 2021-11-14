package com.example.manchesterunitedfan.model.service;

import java.math.BigDecimal;

public class UserRegisterServiceModel {
    private String username;
    private String password;
    private String email;
    private String imgUrl;
    private BigDecimal accountBalance;

    public String getUsername() {
        return username;
    }

    public UserRegisterServiceModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterServiceModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public UserRegisterServiceModel setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public UserRegisterServiceModel setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
        return this;
    }
}
