package com.example.manchesterunitedfan.model.service;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class DepositFundsServiceModel {
    private BigDecimal deposit;

    public BigDecimal getDeposit() {
        return deposit;
    }

    public DepositFundsServiceModel setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
        return this;
    }
}
