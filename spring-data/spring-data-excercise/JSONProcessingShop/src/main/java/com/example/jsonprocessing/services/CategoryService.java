package com.example.jsonprocessing.services;

import com.example.jsonprocessing.models.dtos.CategoryStatsDto;
import com.example.jsonprocessing.models.entities.Category;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface CategoryService {
    void seedCategories() throws IOException;
    Set<Category> findRandomSet();
    List<CategoryStatsDto> getStatsPerCategories();
}
