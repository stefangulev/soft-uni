package com.example.manchesterunitedfan.model.binding;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ChangePasswordBindingModel {
    private String password;
    private String confrimPassword;

    @NotBlank
    @Size(min = 3)
    public String getPassword() {
        return password;
    }


    public ChangePasswordBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }
    @NotBlank
    @Size(min = 3)
    public String getConfrimPassword() {
        return confrimPassword;
    }

    public ChangePasswordBindingModel setConfrimPassword(String confrimPassword) {
        this.confrimPassword = confrimPassword;
        return this;
    }
}
