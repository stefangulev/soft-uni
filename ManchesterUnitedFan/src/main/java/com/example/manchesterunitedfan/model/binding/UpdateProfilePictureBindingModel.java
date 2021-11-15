package com.example.manchesterunitedfan.model.binding;

import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UpdateProfilePictureBindingModel {
    private String imgUrl;


    @NotBlank
    @Size(min = 10)
    public String getImgUrl() {
        return imgUrl;
    }

    public UpdateProfilePictureBindingModel setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }
}
