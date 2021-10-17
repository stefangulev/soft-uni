package com.example.supermarket.repositories;

import com.example.supermarket.model.Category;
import com.example.supermarket.model.Town;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findCategoryByName(String name);
}
