package com.example.manchesterunitedfan.repository;

import com.example.manchesterunitedfan.model.entities.NewsArticleEntity;
import com.example.manchesterunitedfan.model.view.NewsArticleView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface NewsArticleRepository extends JpaRepository<NewsArticleEntity, Long> {
    List<NewsArticleEntity> findAllByOrderByCreatedDesc();
    @Query(value = "SELECT * FROM news_articles a ORDER BY created DESC LIMIT 1", nativeQuery = true)
    NewsArticleEntity findLatestStory();
}
