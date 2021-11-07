package com.example.manchesterunitedfan.model.binding;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UpdateProfileBindingModel {
    private String password;
    private String confirmPassword;
    private String imgUrl;

    @NotBlank
    @Size(min = 3)
    public String getPassword() {
        return password;
    }

    public UpdateProfileBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }

    @NotBlank
    @Size(min = 3)
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UpdateProfileBindingModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public UpdateProfileBindingModel setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }
}
