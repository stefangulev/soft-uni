package com.example.xmlprocessingshop.models.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductsInRangeDto {
    @XmlElement(name = "product")
    List<ProductsInRangeSingleDto> products;

    public ProductsInRangeDto() {

    }

    public List<ProductsInRangeSingleDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsInRangeSingleDto> products) {
        this.products = products;
    }
}
