package com.example.manchesterunitedfan.model.service;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class UpdateProfilePictureServiceModel {
    private String imgUrl;

    public String getImgUrl() {
        return imgUrl;
    }

    public UpdateProfilePictureServiceModel setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }
}
