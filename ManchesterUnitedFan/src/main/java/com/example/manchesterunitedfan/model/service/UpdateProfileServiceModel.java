package com.example.manchesterunitedfan.model.service;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UpdateProfileServiceModel {
    private String password;
    private String imgUrl;

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
