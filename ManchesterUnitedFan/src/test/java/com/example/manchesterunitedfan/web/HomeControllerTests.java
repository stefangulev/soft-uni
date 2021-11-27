package com.example.manchesterunitedfan.web;

import com.example.manchesterunitedfan.model.entities.NewsArticleEntity;
import com.example.manchesterunitedfan.repository.NewsArticleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTests {

    private final String NEWS_ARTICLE_TITLE = "Title for the news article";
    private final String NEWS_ARTICLE_TEXT = "Text message that has at least thirty characters should be entered here!";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private NewsArticleRepository newsArticleRepository;

    @Test
    void getHome() throws Exception {
        initArticle();
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    NewsArticleEntity initArticle() {
        NewsArticleEntity testArticle = new NewsArticleEntity()
                .setCreated(LocalDateTime.now())
                .setTitle(NEWS_ARTICLE_TITLE)
                .setText(NEWS_ARTICLE_TEXT);
        return newsArticleRepository.save(testArticle);
    }
}
