package com.example.andreys_exam_db.services;

import com.example.andreys_exam_db.model.Product;
import com.example.andreys_exam_db.model.service.AddProductServiceModel;
import com.example.andreys_exam_db.model.view.ProductDetailsView;
import com.example.andreys_exam_db.model.view.ProductView;
import com.example.andreys_exam_db.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;


    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addProduct(AddProductServiceModel addProductServiceModel) {
        Product product = new Product().setName(addProductServiceModel.getName())
                .setPrice(addProductServiceModel.getPrice())
                .setCategory(addProductServiceModel.getCategory())
                .setDescription(addProductServiceModel.getDescription())
                .setSex(addProductServiceModel.getSex());

        productRepository.save(product);
    }

    @Override
    public List<ProductView> findAllProducts() {
        return productRepository.findAll().stream().map(p -> modelMapper.map(p, ProductView.class)).collect(Collectors.toList());
    }

    @Override
    public ProductDetailsView findProductDetails(UUID id) {
        Product product = productRepository.findById(id).orElse(null);
        return modelMapper.map(product, ProductDetailsView.class);
    }

    @Override
    public void deleteProduct(UUID id) {
        productRepository.deleteById(id);
    }
}
