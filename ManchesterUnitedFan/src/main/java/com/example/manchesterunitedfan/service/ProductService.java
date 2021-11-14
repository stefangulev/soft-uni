package com.example.manchesterunitedfan.service;

import com.example.manchesterunitedfan.model.entities.ProductEntity;
import com.example.manchesterunitedfan.model.view.ProductCardView;
import com.example.manchesterunitedfan.model.view.ProductDetailsView;

import java.util.List;

public interface ProductService {
    List<ProductCardView> findAllProducts();
    ProductDetailsView getProductViewById(Long id);
    ProductEntity getProductEntityById(Long id);

    void soldProduct(ProductEntity product);
}
