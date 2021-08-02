package com.example.xmlprocessingshop.repositories;

import com.example.xmlprocessingshop.models.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Category, Long> {
}
