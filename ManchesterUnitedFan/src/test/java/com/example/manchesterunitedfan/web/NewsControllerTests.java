package com.example.manchesterunitedfan.web;

import com.example.manchesterunitedfan.model.binding.AddNewsBindingModel;
import com.example.manchesterunitedfan.model.entities.NewsArticleEntity;
import com.example.manchesterunitedfan.model.entities.UserEntity;
import com.example.manchesterunitedfan.model.entities.UserRoleEntity;
import com.example.manchesterunitedfan.model.enums.UserRoleEnum;
import com.example.manchesterunitedfan.repository.NewsArticleRepository;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.manchesterunitedfan.repository.UserRepository;
import com.example.manchesterunitedfan.repository.UserRoleRepository;
import com.example.manchesterunitedfan.service.impl.NewsArticleServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@SpringBootTest
@AutoConfigureMockMvc
public class NewsControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private NewsArticleRepository newsArticleRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private UserRepository userRepository;
    private final String NEWS_ARTICLE_TITLE = "A title for the news article";
    private final String NEWS_ARTICLE_TEXT = "A text message that has at least thirty characters should be entered here!";
    private final String UPDATED_NEWS_ARTICLE_TITLE = "An updated title for the news article";
    private final String UPDATED_NEWS_ARTICLE_TEXT = "An updated text message that has at least thirty characters should be entered here!";



    @AfterEach
    void removeAll() {
        newsArticleRepository.deleteAll();
        userRepository.deleteAll();
        userRoleRepository.deleteAll();
    }

    @WithMockUser(value = "stefan", roles = {"ADMIN", "USER"})
    @Test
    void getNewsPageAuthorized() throws Exception {
        mockMvc.
                perform(get("/news"))
                .andExpect(status().isOk())
                .andExpect(view().name("news"))
                .andExpect(model().attributeExists("newsArticles"));
    }
    @Test
    void getNewsPageAnonymous() throws Exception {
        mockMvc.
                perform(get("/news"))
                .andExpect(status().is3xxRedirection());
    }
    @WithMockUser(value = "stefan", roles = {"ADMIN", "USER"})
    @Test
    void getDetailsPageAuthorized() throws Exception {
        NewsArticleEntity newsArticleEntity = initArticle();
        mockMvc.perform(get("/news/details/" + newsArticleEntity.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("news-article-details"))
                .andExpect(model().attributeExists("article"));
    }
    @Test
    void getDetailsPageAnonymous() throws Exception {
        NewsArticleEntity newsArticleEntity = initArticle();
        mockMvc.perform(get("/news/details/" + newsArticleEntity.getId()))
                .andExpect(status().is3xxRedirection());
    }
    @WithMockUser(value = "stefan", roles = {"ADMIN", "USER"})
    @Test
    void getAddNewsPageAuthorized() throws Exception {
        mockMvc.
                perform(get("/news/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-news-article"));
    }
    @WithMockUser(value = "stefan", roles = {"USER"})
    @Test
    void getAddNewsUnauthorized() throws Exception {
        mockMvc.
                perform(get("/news/add"))
                .andExpect(status().isForbidden());
    }
    @WithMockUser(value = "stefan", roles = {"ADMIN", "USER"})
    @Test
    void postAddNewsAuthorized() throws Exception {
        mockMvc.
                perform(post("/news/add")
                .param("title", NEWS_ARTICLE_TITLE)
                .param("text", NEWS_ARTICLE_TEXT)
                .with(csrf())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection());

        Assertions.assertEquals(newsArticleRepository.count(), 1);
        NewsArticleEntity latestStory = newsArticleRepository.findLatestStory();
        Assertions.assertNotNull(latestStory);
        Assertions.assertEquals(latestStory.getTitle(),NEWS_ARTICLE_TITLE);
        Assertions.assertEquals(latestStory.getText(),NEWS_ARTICLE_TEXT);

    }
    @WithMockUser(value = "stefan", roles = {"ADMIN", "USER"})
    @Test
    void postAddNewsAuthorizedInvalidInput() throws Exception {
        mockMvc.
                perform(post("/news/add")
                        .param("title", "inv")
                        .param("text", "invalid")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attributeExists("addNewsBindingModel"));

        Assertions.assertEquals(newsArticleRepository.count(), 0);
        NewsArticleEntity latestStory = newsArticleRepository.findLatestStory();
        Assertions.assertNull(latestStory);

    }
    @WithMockUser(value = "stefan", roles = {"USER"})
    @Test
    void postAddNewsUnauthorized() throws Exception {
        mockMvc.
                perform(post("/news/add")
                        .param("title", NEWS_ARTICLE_TITLE)
                        .param("text", NEWS_ARTICLE_TEXT)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isForbidden());
    }
    @WithMockUser(value = "stefan", roles = {"ADMIN", "USER"})
    @Test
    void getEditNewsPageAuthorized() throws Exception {
        NewsArticleEntity newsArticleEntity = initArticle();
        mockMvc.
                perform(get("/news/edit/" + newsArticleEntity.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("edit-news-article"));
    }
    @WithMockUser(value = "stefan", roles = {"USER"})
    @Test
    void getEditNewsUnauthorized() throws Exception {
        NewsArticleEntity newsArticleEntity = initArticle();
        mockMvc.
                perform(get("/news/edit/" + newsArticleEntity.getId()))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(value = "stefan", roles = {"ADMIN", "USER"})
    @Test
    void patchEditNewsAuthorized() throws Exception {
        UserRoleEntity adminRole = initAdminRole();

        initUserEntity(adminRole);
        NewsArticleEntity newsArticleEntity = initArticle();
        mockMvc.
                perform(patch("/news/edit/" + newsArticleEntity.getId())
                        .param("title", UPDATED_NEWS_ARTICLE_TITLE)
                        .param("text", UPDATED_NEWS_ARTICLE_TEXT)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection());

        Assertions.assertEquals(newsArticleRepository.count(), 1);
        NewsArticleEntity latestStory = newsArticleRepository.findLatestStory();
        Assertions.assertNotNull(latestStory);
        Assertions.assertEquals(latestStory.getTitle(),UPDATED_NEWS_ARTICLE_TITLE);
        Assertions.assertEquals(latestStory.getText(),UPDATED_NEWS_ARTICLE_TEXT);

    }
    @WithMockUser(value = "stefan", roles = {"ADMIN", "USER"})
    @Test
    void patchEditNewsInvalidInput() throws Exception {
        UserRoleEntity adminRole = initAdminRole();

        initUserEntity(adminRole);
        NewsArticleEntity newsArticleEntity = initArticle();
        mockMvc.
                perform(patch("/news/edit/" + newsArticleEntity.getId())
                        .param("title", "inv")
                        .param("text", "invalid")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attributeExists("article"));;

        Assertions.assertEquals(newsArticleRepository.count(), 1);
        NewsArticleEntity latestStory = newsArticleRepository.findLatestStory();
        Assertions.assertNotNull(latestStory);
        Assertions.assertEquals(latestStory.getTitle(),NEWS_ARTICLE_TITLE);
        Assertions.assertEquals(latestStory.getText(),NEWS_ARTICLE_TEXT);

    }
    @WithMockUser(value = "stefan", roles = {"USER"})
    @Test
    void patchEditNewsUnauthorized() throws Exception {
        UserRoleEntity userRole = initUserRole();
        initUserEntity(userRole);
        NewsArticleEntity newsArticleEntity = initArticle();
        mockMvc.
                perform(patch("/news/edit/" + newsArticleEntity.getId())
                        .param("title", NEWS_ARTICLE_TITLE)
                        .param("text", NEWS_ARTICLE_TEXT)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isForbidden());
    }
    @WithMockUser(value = "stefan", roles = {"ADMIN", "USER"})
    @Test
    void deleteArticleAuthorized() throws Exception {
        UserRoleEntity adminRole = initAdminRole();
        initUserEntity(adminRole);
        NewsArticleEntity newsArticleEntity = initArticle();
        mockMvc.perform(delete("/news/delete/" + newsArticleEntity.getId()).with(csrf()))
                .andExpect(status().is3xxRedirection());
        Assertions.assertEquals(newsArticleRepository.count(), 0);
        NewsArticleEntity latestStory = newsArticleRepository.findLatestStory();
        Assertions.assertNull(latestStory);
    }
    @WithMockUser(value = "stefan", roles = {"USER"})
    @Test
    void deleteArticleUnauthorized() throws Exception {
        UserRoleEntity userRole = initUserRole();
        initUserEntity(userRole);
        NewsArticleEntity newsArticleEntity = initArticle();
        mockMvc.perform(delete("/news/delete/" + newsArticleEntity.getId()).with(csrf()))
                .andExpect(status().isForbidden());
    }



    NewsArticleEntity initArticle() {
        NewsArticleEntity testArticle = new NewsArticleEntity()
                .setCreated(LocalDateTime.now())
                .setTitle(NEWS_ARTICLE_TITLE)
                .setText(NEWS_ARTICLE_TEXT);
       return newsArticleRepository.save(testArticle);
    }
    UserRoleEntity initAdminRole() {
        UserRoleEntity admin = new UserRoleEntity().setName(UserRoleEnum.ADMIN);
        return userRoleRepository.save(admin);
    }
    UserRoleEntity initUserRole() {
        UserRoleEntity user = new UserRoleEntity().setName(UserRoleEnum.USER);
        return userRoleRepository.save(user);
    }
    UserEntity initUserEntity(UserRoleEntity userRoleEntity) {
        UserEntity userEntity = new UserEntity()
                .setUsername("stefan")
                .setPassword("12345")
                .setAccountBalance(BigDecimal.ONE)
                .setEmail("stefan@abv.bg");
        userEntity.getRole().add(userRoleEntity);
       return userRepository.save(userEntity);
    }

}
