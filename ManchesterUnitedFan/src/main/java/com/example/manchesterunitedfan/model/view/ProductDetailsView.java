package com.example.manchesterunitedfan.model.view;

import java.math.BigDecimal;

public class ProductDetailsView {
    private Long id;
    private String name;
    private Integer quantity;
    private BigDecimal price;
    private String imgUrl;

    public Long getId() {
        return id;
    }

    public ProductDetailsView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductDetailsView setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public ProductDetailsView setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductDetailsView setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public ProductDetailsView setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }
}
