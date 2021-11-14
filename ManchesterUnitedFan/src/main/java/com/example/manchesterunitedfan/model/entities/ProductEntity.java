package com.example.manchesterunitedfan.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class ProductEntity extends BaseEntity{
    private String name;
    private Integer quantity;
    private BigDecimal price;
    private String imgUrl;

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public ProductEntity setName(String name) {
        this.name = name;
        return this;
    }
    @Column(nullable = false)
    public Integer getQuantity() {
        return quantity;
    }

    public ProductEntity setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    @Column(nullable = false)
    public BigDecimal getPrice() {
        return price;
    }

    public ProductEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    @Column(name = "img_url")
    public String getImgUrl() {
        return imgUrl;
    }

    public ProductEntity setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }
}
