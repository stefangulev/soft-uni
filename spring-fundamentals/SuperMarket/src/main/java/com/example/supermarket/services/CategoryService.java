package com.example.supermarket.services;

import com.example.supermarket.model.Category;

public interface CategoryService {
    String addCategory(String categoryName);
    boolean isCategoryValid(Category category);
}
