package com.example.supermarket.services;

import com.example.supermarket.model.Product;

import java.time.Instant;
import java.time.LocalDate;

public interface ProductService {
    String addProduct(String name, Double price,  LocalDate date, String categoryName);
    boolean isProductValid(Product product);

}
