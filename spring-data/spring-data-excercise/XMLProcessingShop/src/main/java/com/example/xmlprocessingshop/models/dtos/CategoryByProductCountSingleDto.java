package com.example.xmlprocessingshop.models.dtos;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryByProductCountSingleDto {
    @XmlAttribute(name = "name")
    private String name;
    @XmlElement(name = "product-count")
    private Integer productCount;
    @XmlElement(name = "average-price")
    private BigDecimal averagePrice;
    @XmlElement(name = "total-revenue")
    private BigDecimal totalRevenue;

    public CategoryByProductCountSingleDto() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
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
