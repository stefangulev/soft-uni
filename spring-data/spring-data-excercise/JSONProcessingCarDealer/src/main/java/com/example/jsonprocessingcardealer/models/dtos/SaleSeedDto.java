package com.example.jsonprocessingcardealer.models.dtos;

import com.example.jsonprocessingcardealer.models.entities.Car;
import com.example.jsonprocessingcardealer.models.entities.Customer;

public class SaleSeedDto {
    private Double discount;
    private Car car;
    private Customer customer;

    public SaleSeedDto() {

    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
