package com.example.themarket.model.dtos;

import java.math.BigDecimal;

public class CreateUserControllerDto {
    private String username;
    private BigDecimal accountBalance;

    public String getUsername() {
        return username;
    }

    public CreateUserControllerDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public CreateUserControllerDto setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
        return this;
    }
}
