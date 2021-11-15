package com.example.manchesterunitedfan.model.service;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class UpdateProfileServiceModel {
    private String password;
    private String imgUrl;
    private BigDecimal deposit;

    public BigDecimal getDeposit() {
        return deposit;
    }

    public UpdateProfileServiceModel setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UpdateProfileServiceModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public UpdateProfileServiceModel setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }
}
