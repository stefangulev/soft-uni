package com.example.coffeeshop.services.impl;

import com.example.coffeeshop.model.entities.Category;
import com.example.coffeeshop.model.entities.CategoryNameEnum;
import com.example.coffeeshop.repositories.CategoryRepository;
import com.example.coffeeshop.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initCategories() {
        Arrays.stream(CategoryNameEnum.values()).forEach(e -> {
            Category category = new Category().setName(e);
            switch (e) {
                case CAKE:
                    category.setNeededTime(10);
                    break;
                case OTHER:
                    category.setNeededTime(5);
                    break;
                case COFFEE:
                    category.setNeededTime(2);
                    break;
                case DRINK:
                    category.setNeededTime(1);
                    break;
            }
            categoryRepository.save(category);
        });

    }

    @Override
    public Category findCategoryByName(CategoryNameEnum categoryNameEnum) {
        return categoryRepository.findCategoryByName(categoryNameEnum);
    }
}
