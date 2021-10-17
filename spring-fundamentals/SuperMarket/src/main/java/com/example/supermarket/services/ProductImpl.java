package com.example.supermarket.services;

import com.example.supermarket.model.Product;
import com.example.supermarket.repositories.CategoryRepository;
import com.example.supermarket.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.time.Instant;
import java.time.LocalDate;

@Service
public class ProductImpl implements ProductService{
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final Validator validator;

    public ProductImpl(CategoryRepository categoryRepository, ProductRepository productRepository, Validator validator) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.validator = validator;
    }

    @Override
    public String addProduct(String name, Double price, LocalDate date, String categoryName) {
        Product product = new Product(name, price, date);
        product.setCategory(categoryRepository.findCategoryByName(categoryName));
        if (!isProductValid(product)) {
            return "Invalid product!";
        }
        productRepository.save(product);
        return "Successfully added product!";
    }

    @Override
    public boolean isProductValid(Product product) {
        return validator.validate(product).isEmpty();
    }
}
