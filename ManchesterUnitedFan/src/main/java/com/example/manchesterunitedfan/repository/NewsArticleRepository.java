package com.example.manchesterunitedfan.repository;

import com.example.manchesterunitedfan.model.entities.NewsArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsArticleRepository extends JpaRepository<NewsArticleEntity, Long> {
    List<NewsArticleEntity> findAllByOrderByCreatedDesc();
}
