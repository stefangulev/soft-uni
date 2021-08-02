package com.example.jsonprocessingcardealer.models.dtos;

import com.example.jsonprocessingcardealer.models.entities.Sale;
import com.google.gson.annotations.Expose;

import java.time.LocalDateTime;
import java.util.Set;

public class PresentCustomerDto {
    @Expose
    private long id;
    @Expose
    private String name;
    @Expose
    private LocalDateTime birthDate;
    @Expose
    private boolean isYoungDriver;
    @Expose
    private Set<Sale> sales;

    public PresentCustomerDto() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        isYoungDriver = youngDriver;
    }

    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }
}
