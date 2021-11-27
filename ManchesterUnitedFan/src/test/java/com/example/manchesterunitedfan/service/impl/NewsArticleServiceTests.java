package com.example.manchesterunitedfan.service.impl;

import com.example.manchesterunitedfan.model.entities.NewsArticleEntity;
import com.example.manchesterunitedfan.model.view.NewsArticleView;
import com.example.manchesterunitedfan.repository.NewsArticleRepository;
import com.example.manchesterunitedfan.service.exceptions.NewsArticleNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class NewsArticleServiceTests {

    @Mock
    private NewsArticleRepository mockNewsArticleRepository;
    private NewsArticleServiceImpl newsArticleService;
    private final ModelMapper modelMapper = new ModelMapper();
    private NewsArticleEntity testArticle;
    private NewsArticleEntity testArticle2;

    @BeforeEach
    void init() throws InterruptedException {
        newsArticleService = new NewsArticleServiceImpl(mockNewsArticleRepository, modelMapper);
        testArticle = new NewsArticleEntity()
                .setTitle("test title")
                .setText("test text").setCreated(LocalDateTime.now());
        Thread.sleep(2000);
        testArticle2 = new NewsArticleEntity()
                .setTitle("test title 2")
                .setText("test text 2").setCreated(LocalDateTime.now());
    }

    @Test
    void articleByIdNotFound() {
        Assertions.assertThrows(NewsArticleNotFoundException.class, () ->
        newsArticleService.findArticleById(testArticle.getId()));
    }
    @Test
    void articleByIdFound() {
        Mockito.when(mockNewsArticleRepository.findById(testArticle.getId()))
                .thenReturn(Optional.of(testArticle));
        var actual = newsArticleService.findArticleById(testArticle.getId());
        Assertions.assertEquals(actual.getTitle(), testArticle.getTitle());
        Assertions.assertEquals(actual.getText(), testArticle.getText());
    }

    @Test
    void noArticlesAdded() {
        List<NewsArticleView> articlesOrderedByCreateDate =
                newsArticleService.getArticlesOrderedByCreateDate();
        Assertions.assertTrue(articlesOrderedByCreateDate.isEmpty());
    }
    @Test
    void articlesAdded() {
        Mockito.when(mockNewsArticleRepository.findAllByOrderByCreatedDesc())
                .thenReturn(List.of(testArticle, testArticle2));
        var actual = newsArticleService.getArticlesOrderedByCreateDate();
        Assertions.assertEquals(actual.size(), 2);
       String actualTitles = actual.stream().map(NewsArticleView::getTitle).collect(Collectors.joining(", "));
       String testTitles = "test title, test title 2";
       Assertions.assertEquals(actualTitles, testTitles);
    }






}
