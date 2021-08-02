package com.example.xmlprocessingshop.services;

import com.example.xmlprocessingshop.models.dtos.CategoryByProductCountDto;
import com.example.xmlprocessingshop.models.dtos.CategoryByProductCountSingleDto;
import com.example.xmlprocessingshop.models.dtos.CategorySeedDto;
import com.example.xmlprocessingshop.models.entities.Category;
import com.example.xmlprocessingshop.models.entities.Product;
import com.example.xmlprocessingshop.repositories.CategoriesRepository;
import com.example.xmlprocessingshop.util.ValidationImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class CategoryImpl implements CategoryService{

    private final ModelMapper modelMapper;
    private final ValidationImpl validation;
    private final CategoriesRepository categoriesRepository;

    public CategoryImpl(ModelMapper modelMapper, ValidationImpl validation, CategoriesRepository categoriesRepository) {
        this.modelMapper = modelMapper;
        this.validation = validation;
        this.categoriesRepository = categoriesRepository;
    }


    @Override
    public void seedCategories(CategorySeedDto categorySeedDto) {
        categorySeedDto.getCategories().stream().filter(validation::isValid).map(d -> modelMapper
                .map(d, Category.class)).forEach(categoriesRepository::save);

    }

    @Override
    public Long getCount() {
        return categoriesRepository.count();
    }
    @Override
    public Set<Category> findRandomSet() {
        int categoryCount = ThreadLocalRandom.current().nextInt(1, 3);
        Set<Category> set = new HashSet<>();
        long categoriesCountInRepo = categoriesRepository.count();

        for (int i = 0; i < categoryCount ; i++) {
            long randomId = ThreadLocalRandom.current().nextLong(1, categoriesCountInRepo +1);
            set.add(categoriesRepository.findById(randomId).orElse(null));
        }
        return set;
    }

    @Override
    public CategoryByProductCountDto getCategoriesByProductCount() {
        List<CategoryByProductCountSingleDto> collect = categoriesRepository.findAll().stream().map(c -> {
            CategoryByProductCountSingleDto map = modelMapper.map(c, CategoryByProductCountSingleDto.class);
            map.setName(c.getName());
            map.setProductCount(c.getProducts().size());
            map.setAveragePrice(BigDecimal.valueOf(c.getProducts().stream().map(Product::getPrice).mapToDouble(BigDecimal::doubleValue).average().orElse(0)));
            map.setTotalRevenue(BigDecimal.valueOf(c.getProducts().stream().map(Product::getPrice).mapToDouble(BigDecimal::doubleValue).sum()));
            return map;
        }).sorted((l,r) -> Long.compare(r.getProductCount(), l.getProductCount())).collect(Collectors.toList());

        CategoryByProductCountDto categoryByProductCountDto = new CategoryByProductCountDto();
        categoryByProductCountDto.setCategories(collect);
        return categoryByProductCountDto;
    }
}
