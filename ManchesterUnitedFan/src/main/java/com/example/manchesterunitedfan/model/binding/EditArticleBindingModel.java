package com.example.manchesterunitedfan.model.binding;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class EditArticleBindingModel {

    private Long id;
    private String title;
    private String imgUrl;
    private String text;
    @NotBlank
    @Size(min = 5)
    public String getTitle() {
        return title;
    }

    public Long getId() {
        return id;
    }

    public EditArticleBindingModel setId(Long id) {
        this.id = id;
        return this;
    }

    public EditArticleBindingModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public EditArticleBindingModel setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    @NotBlank
    @Size(min = 30)
    public String getText() {
        return text;
    }

    public EditArticleBindingModel setText(String text) {
        this.text = text;
        return this;
    }
}

