package com.example.manchesterunitedfan.service.impl;

import com.example.manchesterunitedfan.model.entities.ProductEntity;
import com.example.manchesterunitedfan.model.service.AddProductServiceModel;
import com.example.manchesterunitedfan.model.service.UpdateProductServiceModel;
import com.example.manchesterunitedfan.model.view.ProductCardView;
import com.example.manchesterunitedfan.model.view.ProductDetailsView;
import com.example.manchesterunitedfan.repository.ProductRepository;
import com.example.manchesterunitedfan.service.ProductService;
import com.example.manchesterunitedfan.service.exceptions.ProductNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
    public List<ProductCardView> findAllActiveProducts() {
        return productRepository.findAll().stream().filter(p -> !p.isDisabled())
                .map(p -> modelMapper.map(p , ProductCardView.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDetailsView getProductViewById(Long id) {
        return productRepository.findById(id)
                .map(p -> modelMapper.map(p, ProductDetailsView.class))
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found!"));
    }

    @Override
    public ProductEntity getProductEntityById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found!"));
    }

    @Override
    public void soldProduct(ProductEntity product) {
        product.setQuantity(product.getQuantity() - 1);
            productRepository.save(product);
    }

    @Override
    public void createProduct(AddProductServiceModel serviceModel) {
       productRepository.save(modelMapper.map(serviceModel, ProductEntity.class));
    }

    @Override
    public void updateProduct(UpdateProductServiceModel serviceModel) {
        ProductEntity productEntity = getProductEntityById(serviceModel.getId());
        productEntity.setName(serviceModel.getName()).setQuantity(serviceModel.getQuantity()).setPrice(serviceModel.getPrice())
                .setImgUrl(serviceModel.getImgUrl());
        productRepository.save(productEntity);
    }

    @Override
    public void disableProduct(Long id) {
        ProductEntity productEntity = getProductEntityById(id)
        .setDisabled(true);
        productRepository.save(productEntity);
    }

    @Override
    public void enableProduct(Long id) {
        ProductEntity productEntity = getProductEntityById(id)
                .setDisabled(false);
        productRepository.save(productEntity);
    }

    @Override
    public List<ProductCardView> findAllProducts() {
        return productRepository.findAll().stream()
                .map(p -> modelMapper.map(p, ProductCardView.class))
                .collect(Collectors.toList());
    }


}
