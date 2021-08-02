package com.example.jsonprocessing.repositories;

import com.example.jsonprocessing.models.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
