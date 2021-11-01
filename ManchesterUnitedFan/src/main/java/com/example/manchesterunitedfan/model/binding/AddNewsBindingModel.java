package com.example.manchesterunitedfan.model.binding;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AddNewsBindingModel {
    private String title;
    private String imgUrl;
    private String text;

    @NotBlank
    @Size(min = 5)
    public String getTitle() {
        return title;
    }

    public AddNewsBindingModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public AddNewsBindingModel setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    @NotBlank
    @Size(min = 30)
    public String getText() {
        return text;
    }

    public AddNewsBindingModel setText(String text) {
        this.text = text;
        return this;
    }
}
