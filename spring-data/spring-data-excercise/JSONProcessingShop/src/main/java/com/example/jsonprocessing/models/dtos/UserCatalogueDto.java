package com.example.jsonprocessing.models.dtos;

import com.google.gson.annotations.Expose;

import java.util.Set;

public class UserCatalogueDto {
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private String age;
    @Expose
    private Set<ProductCatalogueDto> soldProducts;

    public UserCatalogueDto() {

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

    public Set<ProductCatalogueDto> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(Set<ProductCatalogueDto> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
