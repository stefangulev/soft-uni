package com.example.supermarket.services;

import com.example.supermarket.model.Category;
import com.example.supermarket.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import javax.validation.Validator;

@Service
public class CategoryImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final Validator validator;

    public CategoryImpl(CategoryRepository categoryRepository, Validator validator) {
        this.categoryRepository = categoryRepository;
        this.validator = validator;
    }


    @Override
    public String addCategory(String categoryName) {
        Category category = new Category(categoryName);
        if (!isCategoryValid(category)) {
            return "Name must be minimum two characters!";
        }

        categoryRepository.save(category);
        return "Successfully added category!";
    }

    @Override
    public boolean isCategoryValid(Category category) {
        return validator.validate(category).isEmpty();
    }
}
