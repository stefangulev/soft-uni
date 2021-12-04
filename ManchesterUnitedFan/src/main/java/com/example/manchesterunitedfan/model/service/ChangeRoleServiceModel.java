package com.example.manchesterunitedfan.model.service;

import javax.validation.constraints.NotBlank;

public class ChangeRoleServiceModel {
    String roleSelect;

    public String getRoleSelect() {
        return roleSelect;
    }

    public ChangeRoleServiceModel setRoleSelect(String roleSelect) {
        this.roleSelect = roleSelect;
        return this;
    }
}
