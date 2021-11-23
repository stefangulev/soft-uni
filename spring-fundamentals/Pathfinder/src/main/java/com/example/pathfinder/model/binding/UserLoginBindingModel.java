package com.example.pathfinder.model.binding;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserLoginBindingModel {

    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public UserLoginBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

    @NotBlank
    public String getPassword() {
        return password;
    }

    public UserLoginBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }
}
