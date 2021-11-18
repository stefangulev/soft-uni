package com.example.manchesterunitedfan.model.view;

import java.math.BigDecimal;

public class ProductCardView {
    private Long id;
    private String name;
    private BigDecimal price;
    private String imgUrl;
    private boolean disabled;

    public Long getId() {
        return id;
    }

    public ProductCardView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductCardView setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductCardView setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public ProductCardView setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public ProductCardView setDisabled(boolean disabled) {
        this.disabled = disabled;
        return this;
    }
}
