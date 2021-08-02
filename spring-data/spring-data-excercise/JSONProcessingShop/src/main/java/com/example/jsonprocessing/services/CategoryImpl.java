package com.example.jsonprocessing.services;

import com.example.jsonprocessing.models.dtos.CategorySeedDto;
import com.example.jsonprocessing.models.dtos.CategoryStatsDto;
import com.example.jsonprocessing.models.entities.Category;
import com.example.jsonprocessing.models.entities.Product;
import com.example.jsonprocessing.repositories.CategoryRepository;
import com.example.jsonprocessing.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;


@Service
public class CategoryImpl implements CategoryService{

    private final String PATH_NAME = "src/main/resources/files/categories.json";
    private final Gson gson;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public CategoryImpl(Gson gson, CategoryRepository categoryRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.gson = gson;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }


    @Override
    public void seedCategories() throws IOException {
        if(categoryRepository.count() > 0) {
            return;
        }
        Arrays.stream(gson.fromJson(Files.readString(Path.of(PATH_NAME)), CategorySeedDto[].class))
                .filter(validationUtil::isValid).map(d -> modelMapper.map(d, Category.class)).forEach(categoryRepository::save);
    }

    @Override
    public Set<Category> findRandomSet() {
        int categoryCount = ThreadLocalRandom.current().nextInt(1, 3);
        Set<Category> set = new HashSet<>();
        long categoriesCountInRepo = categoryRepository.count();

        for (int i = 0; i < categoryCount ; i++) {
            long randomId = ThreadLocalRandom.current().nextLong(1, categoriesCountInRepo +1);
            set.add(categoryRepository.findById(randomId).orElse(null));
        }
        return set;
    }

    @Override
    public List<CategoryStatsDto> getStatsPerCategories() {
       return categoryRepository.findAll().stream().map(c -> {
            CategoryStatsDto dto = modelMapper.map(c, CategoryStatsDto.class);
            dto.setCategory(c.getName());
            dto.setProductsCount(c.getProducts().size());
            dto.setAveragePrice(BigDecimal.valueOf(c.getProducts().stream().map(Product::getPrice).mapToDouble(BigDecimal::doubleValue).average().orElse(0)));
            dto.setTotalRevenue(BigDecimal.valueOf(c.getProducts().stream().map(Product::getPrice).mapToDouble(BigDecimal::doubleValue).sum()));
            return dto;
        }).collect(Collectors.toList());
    }
}
