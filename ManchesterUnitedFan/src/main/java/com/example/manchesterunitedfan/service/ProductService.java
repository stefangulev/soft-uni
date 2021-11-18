package com.example.manchesterunitedfan.service;

import com.example.manchesterunitedfan.model.entities.ProductEntity;
import com.example.manchesterunitedfan.model.service.AddProductServiceModel;
import com.example.manchesterunitedfan.model.service.UpdateProductServiceModel;
import com.example.manchesterunitedfan.model.view.ProductCardView;
import com.example.manchesterunitedfan.model.view.ProductDetailsView;

import java.util.List;

public interface ProductService {
    List<ProductCardView> findAllActiveProducts();
    ProductDetailsView getProductViewById(Long id);
    ProductEntity getProductEntityById(Long id);

    void soldProduct(ProductEntity product);

    void createProduct(AddProductServiceModel serviceModel);

    void updateProduct(UpdateProductServiceModel serviceModel);

    void disableProduct(Long id);

    void enableProduct(Long id);

    List<ProductCardView> findAllProducts();
}
