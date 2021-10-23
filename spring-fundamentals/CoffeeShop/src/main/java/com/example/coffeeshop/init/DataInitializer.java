package com.example.coffeeshop.init;

import com.example.coffeeshop.repositories.CategoryRepository;
import com.example.coffeeshop.services.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;

    public DataInitializer(CategoryRepository categoryRepository, CategoryService categoryService) {
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
     if (categoryRepository.count() == 0) {
         categoryService.initCategories();
     }
    }
}
