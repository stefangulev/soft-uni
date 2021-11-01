package com.example.manchesterunitedfan.model.service;

public class AddNewsServiceModel {
    private String title;
    private String imgUrl;
    private String text;

    public String getTitle() {
        return title;
    }

    public AddNewsServiceModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public AddNewsServiceModel setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public String getText() {
        return text;
    }

    public AddNewsServiceModel setText(String text) {
        this.text = text;
        return this;
    }
}
