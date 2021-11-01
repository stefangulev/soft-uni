package com.example.manchesterunitedfan.model.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "news_articles")
public class NewsArticleEntity extends BaseEntity{
    private String title;
    private String imgUrl;
    private String text;
    private LocalDateTime created;
    private LocalDateTime updated;

    @Column(nullable = false)
    public String getTitle() {
        return title;
    }

    public NewsArticleEntity setTitle(String title) {
        this.title = title;
        return this;
    }
    @Column(name = "img_url")
    public String getImgUrl() {
        return imgUrl;
    }

    public NewsArticleEntity setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }
    @Column(nullable = false, columnDefinition = "LONGTEXT")
    public String getText() {
        return text;
    }

    public NewsArticleEntity setText(String text) {
        this.text = text;
        return this;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public NewsArticleEntity setCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public NewsArticleEntity setUpdated(LocalDateTime updated) {
        this.updated = updated;
        return this;
    }
    @PrePersist
    public void setTimes() {
        this.created = LocalDateTime.now();
        this.updated = LocalDateTime.now();
    }
    @PreUpdate
    public void setUpdated() {
        this.updated = LocalDateTime.now();
    }
}
