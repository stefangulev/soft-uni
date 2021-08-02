package com.example.xmlprocessingshop.services;

import com.example.xmlprocessingshop.models.dtos.CategoryByProductCountDto;
import com.example.xmlprocessingshop.models.dtos.CategorySeedDto;
import com.example.xmlprocessingshop.models.entities.Category;

import java.util.Set;

public interface CategoryService {
    void seedCategories(CategorySeedDto categorySeedDto);
    Long getCount();
    Set<Category> findRandomSet();

    CategoryByProductCountDto getCategoriesByProductCount();
}
