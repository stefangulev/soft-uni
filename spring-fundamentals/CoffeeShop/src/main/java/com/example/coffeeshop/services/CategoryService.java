package com.example.coffeeshop.services;

import com.example.coffeeshop.model.entities.Category;
import com.example.coffeeshop.model.entities.CategoryNameEnum;

public interface CategoryService {
    void initCategories();
    Category findCategoryByName(CategoryNameEnum categoryNameEnum);
}
