package com.example.manchesterunitedfan.model.service;

public class EditArticleServiceModel {

    private String title;
    private String imgUrl;
    private String text;

    public String getTitle() {
        return title;
    }

    public EditArticleServiceModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public EditArticleServiceModel setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public String getText() {
        return text;
    }

    public EditArticleServiceModel setText(String text) {
        this.text = text;
        return this;
    }
}

