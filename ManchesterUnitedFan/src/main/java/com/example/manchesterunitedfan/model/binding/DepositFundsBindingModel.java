package com.example.manchesterunitedfan.model.binding;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class DepositFundsBindingModel {
    private BigDecimal deposit;

    @NotNull
    @Min(1)
    public BigDecimal getDeposit() {
        return deposit;
    }

    public DepositFundsBindingModel setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
        return this;
    }
}
