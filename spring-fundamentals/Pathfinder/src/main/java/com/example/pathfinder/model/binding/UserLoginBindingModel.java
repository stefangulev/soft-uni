package com.example.pathfinder.model.binding;

import javax.validation.constraints.NotNull;

public class UserLoginBindingModel {

    private String username;
    private String password;

    @NotNull
    public String getUsername() {
        return username;
    }

    public UserLoginBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    @NotNull
    public String getPassword() {
        return password;
    }

    public UserLoginBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }
}
