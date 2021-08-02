package com.example.xmlprocessingshop.services;

import com.example.xmlprocessingshop.models.dtos.ProductSeedDto;
import com.example.xmlprocessingshop.models.dtos.ProductsInRangeDto;



public interface ProductService {
    void seedProducts(ProductSeedDto productSeedDto);
    Long getCount();

    ProductsInRangeDto getProductsInRange();
}
