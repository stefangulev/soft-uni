package com.example.xmlprocessingshop.models.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategorySeedDto {

    @XmlElement(name = "category")
    List<SingleCategorySeedDto> categories;

    public CategorySeedDto() {

    }

    public List<SingleCategorySeedDto> getCategories() {
        return categories;
    }

    public void setCategories(List<SingleCategorySeedDto> categories) {
        this.categories = categories;
    }
}
