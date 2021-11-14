package com.example.manchesterunitedfan.service.impl;

import com.example.manchesterunitedfan.model.entities.ProductEntity;
import com.example.manchesterunitedfan.model.view.ProductCardView;
import com.example.manchesterunitedfan.model.view.ProductDetailsView;
import com.example.manchesterunitedfan.repository.ProductRepository;
import com.example.manchesterunitedfan.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ProductCardView> findAllProducts() {
        return productRepository.findAll().stream()
                .map(p -> modelMapper.map(p , ProductCardView.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDetailsView getProductViewById(Long id) {
        return productRepository.findById(id)
                .map(p -> modelMapper.map(p, ProductDetailsView.class)).orElse(null);
    }

    @Override
    public ProductEntity getProductEntityById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void soldProduct(ProductEntity product) {
        product.setQuantity(product.getQuantity() - 1);
            productRepository.save(product);
    }
}
