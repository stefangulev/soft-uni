package com.example.springdataintroexcercise.service;

import com.example.springdataintroexcercise.models.Category;

import java.io.IOException;
import java.util.Set;

public interface CategoryService {
    void seedCategories() throws IOException;

    Set<Category> getRandomCategory();
}
