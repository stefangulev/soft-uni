package com.example.demo.models.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "supplier")
@XmlAccessorType(XmlAccessType.FIELD)
public class LocalSupplierSingleDto {
    @XmlAttribute(name = "id")
    private Long id;
    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "parts-count")
    private Integer partCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPartCount() {
        return partCount;
    }

    public void setPartCount(Integer partCount) {
        this.partCount = partCount;
    }
}
