package com.example.coffeeshop.repositories;

import com.example.coffeeshop.model.entities.Category;
import com.example.coffeeshop.model.entities.CategoryNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findCategoryByName(CategoryNameEnum categoryNameEnum);
}
