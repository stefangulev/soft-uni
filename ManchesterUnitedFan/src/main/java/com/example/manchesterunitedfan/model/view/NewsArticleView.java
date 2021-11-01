package com.example.manchesterunitedfan.model.view;

import java.time.LocalDateTime;

public class NewsArticleView {
    private Long id;
    private String title;
    private String imgUrl;
    private String text;
    private LocalDateTime created;
    private LocalDateTime updated;

    public Long getId() {
        return id;
    }

    public NewsArticleView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public NewsArticleView setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public NewsArticleView setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public String getText() {
        return text;
    }

    public NewsArticleView setText(String text) {
        this.text = text;
        return this;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public NewsArticleView setCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public NewsArticleView setUpdated(LocalDateTime updated) {
        this.updated = updated;
        return this;
    }
}
