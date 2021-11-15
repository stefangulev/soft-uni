package com.example.manchesterunitedfan.model.service;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class ChangePasswordServiceModel {
    private String password;
    private String confrimPassword;

    public String getPassword() {
        return password;
    }

    public ChangePasswordServiceModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfrimPassword() {
        return confrimPassword;
    }

    public ChangePasswordServiceModel setConfrimPassword(String confrimPassword) {
        this.confrimPassword = confrimPassword;
        return this;
    }
}
