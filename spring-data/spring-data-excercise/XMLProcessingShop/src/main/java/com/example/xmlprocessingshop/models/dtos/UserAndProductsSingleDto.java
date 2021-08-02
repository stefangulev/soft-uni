package com.example.xmlprocessingshop.models.dtos;

import javax.xml.bind.annotation.*;
import java.util.Set;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserAndProductsSingleDto {
    @XmlAttribute(name = "first-name")
    private String firstName;
    @XmlAttribute(name = "last-name")
    private String lastName;
    @XmlAttribute(name = "age")
    private String age;
    @XmlElementWrapper(name = "sold-products")
    @XmlElement(name = "product")
    Set<UserAndProductsSoldProduct> soldProducts;
    @XmlAttribute(name = "count")
    private Long productCount;

    public UserAndProductsSingleDto() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Set<UserAndProductsSoldProduct> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(Set<UserAndProductsSoldProduct> soldProducts) {
        this.soldProducts = soldProducts;
    }

    public Long getProductCount() {
        return productCount;
    }

    public void setProductCount(Long productCount) {
        this.productCount = productCount;
    }
}
