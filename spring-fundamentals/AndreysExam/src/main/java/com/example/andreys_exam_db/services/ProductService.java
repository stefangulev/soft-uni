package com.example.andreys_exam_db.services;

import com.example.andreys_exam_db.model.service.AddProductServiceModel;
import com.example.andreys_exam_db.model.view.ProductDetailsView;
import com.example.andreys_exam_db.model.view.ProductView;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    void addProduct(AddProductServiceModel addProductServiceModel);
    List<ProductView> findAllProducts();
    ProductDetailsView findProductDetails(UUID id);
    void deleteProduct(UUID id);
}
