package com.example.manchesterunitedfan.service.impl;

import com.example.manchesterunitedfan.model.entities.NewsArticleEntity;
import com.example.manchesterunitedfan.model.service.AddNewsServiceModel;
import com.example.manchesterunitedfan.model.service.EditArticleServiceModel;
import com.example.manchesterunitedfan.model.view.NewsArticleView;
import com.example.manchesterunitedfan.repository.NewsArticleRepository;
import com.example.manchesterunitedfan.service.NewsArticleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsArticleServiceImpl implements NewsArticleService {
    private final NewsArticleRepository newsArticleRepository;
    private final ModelMapper modelMapper;

    public NewsArticleServiceImpl(NewsArticleRepository newsArticleRepository, ModelMapper modelMapper) {
        this.newsArticleRepository = newsArticleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addArticle(AddNewsServiceModel addNewsServiceModel) {
        newsArticleRepository.save(new NewsArticleEntity()
                .setTitle(addNewsServiceModel.getTitle())
                .setImgUrl(addNewsServiceModel.getImgUrl())
                .setText(addNewsServiceModel.getText()));
    }

    @Override
    public List<NewsArticleView> getArticles() {
        return newsArticleRepository.findAllByOrderByCreatedDesc().stream().map(a -> modelMapper.map(a , NewsArticleView.class)).collect(Collectors.toList());
    }

    @Override
    public NewsArticleView findArticleById(Long id) {
        return modelMapper.map(newsArticleRepository.findById(id).orElse(null), NewsArticleView.class);
    }

    @Override
    public void updateNewsArticle(EditArticleServiceModel serviceModel, Long id) {
        NewsArticleEntity newsArticleEntity = newsArticleRepository.findById(id).orElse(null)
                .setTitle(serviceModel.getTitle())
                .setText(serviceModel.getText())
                .setImgUrl(serviceModel.getImgUrl());
        newsArticleRepository.save(newsArticleEntity);
    }

    @Override
    public void deleteArticle(Long id) {
        newsArticleRepository.deleteById(id);
    }
}
