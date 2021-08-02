package com.example.demo.models.dtos;

import com.example.demo.models.entities.Car;
import com.example.demo.models.entities.Customer;

public class SaleSeedDto {
    private Double discount;
    private Car car;
    private Customer customer;

    public SaleSeedDto() {

    }
    public SaleSeedDto(Double discount, Car car, Customer customer) {
        this.discount = discount;
        this.car = car;
        this.customer = customer;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
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
