package com.example.manchesterunitedfan.service;

import com.example.manchesterunitedfan.model.service.AddNewsServiceModel;
import com.example.manchesterunitedfan.model.service.EditArticleServiceModel;
import com.example.manchesterunitedfan.model.view.NewsArticleView;

import java.util.List;

public interface NewsArticleService {
    void addArticle(AddNewsServiceModel addNewsServiceModel);
    List<NewsArticleView> getArticles();
    NewsArticleView findArticleById(Long id);
    void updateNewsArticle(EditArticleServiceModel map, Long id);
    void deleteArticle(Long id);
}
