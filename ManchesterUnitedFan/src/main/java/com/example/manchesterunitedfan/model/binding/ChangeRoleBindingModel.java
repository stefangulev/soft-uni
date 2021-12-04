package com.example.manchesterunitedfan.model.binding;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ChangeRoleBindingModel {
    String roleSelect;

    @NotBlank
    public String getRoleSelect() {
        return roleSelect;
    }

    public ChangeRoleBindingModel setRoleSelect(String roleSelect) {
        this.roleSelect = roleSelect;
        return this;
    }
}
