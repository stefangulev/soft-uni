package com.example.manchesterunitedfan.model.binding;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;

public class UserRegisterBindingModel {
    private String username;
    private String password;
    private String confirmPassword;
    private String email;
    private String imgUrl;
    private BigDecimal accountBalance;

    @NotBlank
    @Size(min = 5, max= 30)
    public String getUsername() {
        return username;
    }

    public UserRegisterBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    @NotBlank
    @Size(min = 3)
    public String getPassword() {
        return password;
    }

    public UserRegisterBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }

    @NotBlank
    @Size(min = 3)
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterBindingModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    @NotBlank
    @Email
    public String getEmail() {
        return email;
    }

    public UserRegisterBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public UserRegisterBindingModel setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    @NotNull
    @Positive
    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public UserRegisterBindingModel setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
        return this;
    }
}
