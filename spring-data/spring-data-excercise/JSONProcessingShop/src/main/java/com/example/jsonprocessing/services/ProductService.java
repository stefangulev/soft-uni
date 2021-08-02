package com.example.jsonprocessing.services;

import com.example.jsonprocessing.models.dtos.ProductWithoutBuyerDto;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    void seedProducts() throws IOException;

    List<ProductWithoutBuyerDto> getProductsWithoutBuyersInRange(BigDecimal lower, BigDecimal upper);
}
