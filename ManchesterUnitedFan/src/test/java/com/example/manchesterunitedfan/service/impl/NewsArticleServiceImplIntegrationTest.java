package com.example.manchesterunitedfan.service.impl;

import com.example.manchesterunitedfan.model.binding.AddNewsBindingModel;
import com.example.manchesterunitedfan.model.entities.UserRoleEntity;
import com.example.manchesterunitedfan.repository.NewsArticleRepository;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
//@WithMockUser(value = "stefan", authorities = {"ADMIN"})
//@SpringBootTest
//@AutoConfigureMockMvc
public class NewsArticleServiceImplIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private NewsArticleRepository newsArticleRepository;

    @Autowired
    private NewsArticleServiceImpl newsArticleService;
    private AddNewsBindingModel addNewsBindingModel;

    @AfterEach
    void removeAll() {
        newsArticleRepository.deleteAll();
    }


    @Test
    void test() throws Exception {
//        addNewsBindingModel = new AddNewsBindingModel().setTitle("Test Title")
//        .setText("Test text that has enough characters to pass validation.");
//        mockMvc.perform(post("http://localhost:8080/news/add", addNewsBindingModel)
//                .with(csrf().asHeader()));
//        Assertions.assertEquals(newsArticleRepository.count(), 1);
    }
}
