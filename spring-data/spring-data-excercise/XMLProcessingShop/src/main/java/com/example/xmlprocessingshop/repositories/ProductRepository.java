package com.example.xmlprocessingshop.repositories;

import com.example.xmlprocessingshop.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByPriceGreaterThanAndPriceLessThanAndBuyerIsNullOrderByPrice(BigDecimal lower, BigDecimal higher);
}
