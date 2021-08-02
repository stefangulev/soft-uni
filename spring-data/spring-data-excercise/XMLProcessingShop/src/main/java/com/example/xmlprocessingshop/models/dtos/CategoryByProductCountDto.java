package com.example.xmlprocessingshop.models.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryByProductCountDto {
    @XmlElement(name = "category")
    List<CategoryByProductCountSingleDto> categories;

    public CategoryByProductCountDto() {

    }

    public List<CategoryByProductCountSingleDto> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryByProductCountSingleDto> categories) {
        this.categories = categories;
    }
}
