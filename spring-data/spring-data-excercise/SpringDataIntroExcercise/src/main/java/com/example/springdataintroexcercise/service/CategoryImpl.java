package com.example.springdataintroexcercise.service;

import com.example.springdataintroexcercise.models.Category;
import com.example.springdataintroexcercise.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CategoryImpl implements CategoryService {

    private final String FILE_PATH = "src/main/resources/files/categories.txt";

    private final CategoryRepository categoryRepository;

    public CategoryImpl( CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public void seedCategories() throws IOException {
        if(categoryRepository.count() == 0) {
            Files.readAllLines(Path.of(FILE_PATH)).stream().filter(row -> !row.isEmpty()).forEach(row -> {
                Category category = new Category(row);
                categoryRepository.save(category);
            });
        }

    }

    @Override
    public Set<Category> getRandomCategory() {
        Map<Integer, Category> categories = new HashMap<>();
        int randInt = ThreadLocalRandom.current().nextInt(1 , 3);
        for (int i = 0; i < randInt ; i++) {
            int id = (int) ThreadLocalRandom.current().nextLong(1, categoryRepository.count());
            Category category = categoryRepository.findById(id).orElse(null);
            if(!categories.containsKey(category.getId())) {
                categories.put(category.getId(), category);
            }
        }
        return new HashSet<>(categories.values());


    }
}
