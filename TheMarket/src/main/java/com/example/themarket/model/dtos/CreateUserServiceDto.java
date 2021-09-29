package com.example.themarket.model.dtos;

import java.math.BigDecimal;

public class CreateUserServiceDto {
    private String username;
    private BigDecimal accountBalance;


    public String getUsername() {
        return username;
    }

    public CreateUserServiceDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public CreateUserServiceDto setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
        return this;
    }
}
