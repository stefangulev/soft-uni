package com.example.jsonprocessing.models.dtos;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;
import java.util.OptionalDouble;

public class CategoryStatsDto {
    @Expose
    private String category;
    @Expose
    private Long productsCount;
    @Expose
    private BigDecimal averagePrice;
    @Expose
    private BigDecimal totalRevenue;

    public CategoryStatsDto() {

    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getProductsCount() {
        return productsCount;
    }

    public void setProductsCount(long productsCount) {
        this.productsCount = productsCount;
    }

    public BigDecimal getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(BigDecimal averagePrice) {
        this.averagePrice = averagePrice;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
