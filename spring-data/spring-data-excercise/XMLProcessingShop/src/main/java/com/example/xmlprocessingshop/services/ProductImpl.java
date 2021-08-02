package com.example.xmlprocessingshop.services;

import com.example.xmlprocessingshop.models.dtos.ProductSeedDto;
import com.example.xmlprocessingshop.models.dtos.ProductsInRangeDto;
import com.example.xmlprocessingshop.models.dtos.ProductsInRangeSingleDto;
import com.example.xmlprocessingshop.models.entities.Product;
import com.example.xmlprocessingshop.repositories.ProductRepository;
import com.example.xmlprocessingshop.util.ValidationImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductImpl implements ProductService{
    private final ModelMapper modelMapper;
    private final ValidationImpl validation;
    private final ProductRepository productRepository;
    private final UserService userService;
    private final CategoryService categoryService;

    public ProductImpl(ModelMapper modelMapper, ValidationImpl validation, ProductRepository productRepository, UserService userService, CategoryService categoryService) {
        this.modelMapper = modelMapper;
        this.validation = validation;
        this.productRepository = productRepository;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedProducts(ProductSeedDto productSeedDto) {
       productSeedDto.getProducts().stream().filter(validation::isValid).map(d -> {
            Product product = modelMapper.map(d, Product.class);
            if (product.getPrice().doubleValue() < 500) {
                product.setBuyer(userService.findRandom());
            }
            product.setSeller(userService.findRandom());
            product.setCategories(categoryService.findRandomSet());
            return product;
        }).forEach(productRepository::save);

    }

    @Override
    public Long getCount() {
        return productRepository.count();
    }

    @Override
    public ProductsInRangeDto getProductsInRange() {
        List<ProductsInRangeSingleDto> products = productRepository.findAllByPriceGreaterThanAndPriceLessThanAndBuyerIsNullOrderByPrice(new BigDecimal(300), new BigDecimal(1000))
                .stream().map(p -> {
                    ProductsInRangeSingleDto map = modelMapper.map(p, ProductsInRangeSingleDto.class);
                    map.setSeller(String.format("%s %s", p.getSeller().getFirstName() == null ? "" :p.getSeller().getFirstName() , p.getSeller().getLastName()));
                    return map;
                }).collect(Collectors.toList());
        ProductsInRangeDto productsInRangeDto = new ProductsInRangeDto();
        productsInRangeDto.setProducts(products);
        return productsInRangeDto;

    }
}
