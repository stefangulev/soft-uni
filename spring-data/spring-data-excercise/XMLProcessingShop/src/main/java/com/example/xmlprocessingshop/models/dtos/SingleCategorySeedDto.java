package com.example.xmlprocessingshop.models.dtos;

import org.hibernate.validator.constraints.Length;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class SingleCategorySeedDto {
    @XmlElement(name = "name")
    @Length(min = 3, max = 15)
    private String name;

    public SingleCategorySeedDto() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
