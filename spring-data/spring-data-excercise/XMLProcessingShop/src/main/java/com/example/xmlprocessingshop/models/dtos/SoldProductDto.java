package com.example.xmlprocessingshop.models.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class SoldProductDto {
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "price")
    private String price;
    @XmlElement(name = "buyer-first-name")
    private String buyerFirstName;
    @XmlElement(name = "buyer-last-name")
    private String buyerLastName;

    private SoldProductDto() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBuyerFirstName() {
        return buyerFirstName;
    }

    public void setBuyerFirstName(String buyerFirstName) {
        this.buyerFirstName = buyerFirstName;
    }

    public String getBuyerLastName() {
        return buyerLastName;
    }

    public void setBuyerLastName(String buyerLastName) {
        this.buyerLastName = buyerLastName;
    }
}
