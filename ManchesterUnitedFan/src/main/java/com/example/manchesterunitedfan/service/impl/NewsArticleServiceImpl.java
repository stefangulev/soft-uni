package com.example.manchesterunitedfan.service.impl;

import com.example.manchesterunitedfan.model.entities.NewsArticleEntity;
import com.example.manchesterunitedfan.model.service.AddNewsServiceModel;
import com.example.manchesterunitedfan.model.service.EditArticleServiceModel;
import com.example.manchesterunitedfan.model.view.NewsArticleView;
import com.example.manchesterunitedfan.repository.NewsArticleRepository;
import com.example.manchesterunitedfan.service.NewsArticleService;
import com.example.manchesterunitedfan.service.exceptions.NewsArticleNotFoundException;
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
    public List<NewsArticleView> getArticlesOrderedByCreateDate() {
        return newsArticleRepository.findAllByOrderByCreatedDesc().stream().map(a -> modelMapper.map(a , NewsArticleView.class)).collect(Collectors.toList());
    }

    @Override
    public NewsArticleView findArticleById(Long id) {
        return modelMapper.map(newsArticleRepository.findById(id)
                .orElseThrow(() -> new NewsArticleNotFoundException("News article with id " + id + " not found!"))
                , NewsArticleView.class);
    }

    @Override
    public void updateNewsArticle(EditArticleServiceModel serviceModel, Long id) {
        NewsArticleEntity newsArticleEntity = newsArticleRepository.findById(id).orElseThrow(() -> new NewsArticleNotFoundException("News article with id " + id + " not found!"))
                .setTitle(serviceModel.getTitle())
                .setText(serviceModel.getText())
                .setImgUrl(serviceModel.getImgUrl());
        newsArticleRepository.save(newsArticleEntity);
    }

    @Override
    public void deleteArticle(Long id) {
        findArticleById(id);
        newsArticleRepository.deleteById(id);
    }

    @Override
    public NewsArticleView findLatestArticle() {

        NewsArticleEntity latestStory = newsArticleRepository.findLatestStory();
        if (latestStory == null) {
            return null;
        } else {
            return modelMapper.map(latestStory, NewsArticleView.class);
        }

    }


}
