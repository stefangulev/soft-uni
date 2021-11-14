package com.example.manchesterunitedfan.model.view;

import com.example.manchesterunitedfan.model.enums.UserRoleEnum;

public class UserRoleView {
    private UserRoleEnum name;

    public UserRoleEnum getName() {
        return name;
    }

    public UserRoleView setName(UserRoleEnum name) {
        this.name = name;
        return this;
    }
}
