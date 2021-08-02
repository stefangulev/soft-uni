package com.example.jsonprocessing.services;

import com.example.jsonprocessing.models.dtos.ProductSeedDto;
import com.example.jsonprocessing.models.dtos.ProductWithoutBuyerDto;
import com.example.jsonprocessing.models.entities.Product;
import com.example.jsonprocessing.repositories.ProductRepository;
import com.example.jsonprocessing.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductImpl implements ProductService{

    private final String PATH_NAME = "src/main/resources/files/products.json";
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;
    private final CategoryService categoryService;
    private final UserService userService;

    public ProductImpl(ProductRepository productRepository, ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson, CategoryService categoryService, UserService userService) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @Override
    public void seedProducts() throws IOException {
        if(productRepository.count() > 0) {
            return;
        }
        Arrays.stream(gson.fromJson(Files.readString(Path.of(PATH_NAME)), ProductSeedDto[].class))
                .filter(validationUtil::isValid).map(d -> {
                    Product product = modelMapper.map(d, Product.class);
                    product.setCategories(categoryService.findRandomSet());
                    if(product.getPrice().compareTo(BigDecimal.valueOf(500L)) <=0) {
                        product.setBuyer(userService.findRandom());
                    }
                    product.setSeller(userService.findRandom());
                    return product;
                }).forEach(productRepository::save);
    }

    @Override
    public List<ProductWithoutBuyerDto> getProductsWithoutBuyersInRange(BigDecimal lower, BigDecimal upper) {
       return productRepository.findAllByPriceGreaterThanAndPriceLessThanAndBuyerIsNullOrderByPrice(lower, upper)
               .stream().map(p -> {
                   ProductWithoutBuyerDto dto = modelMapper.map(p, ProductWithoutBuyerDto.class);
                   dto.setSeller(String.format("%s %s", p.getSeller().getFirstName(), p.getSeller().getLastName()));
                   return dto; }
               ).collect(Collectors.toList());
    }
}
