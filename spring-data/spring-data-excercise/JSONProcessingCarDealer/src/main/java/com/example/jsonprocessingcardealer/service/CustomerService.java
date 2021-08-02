package com.example.jsonprocessingcardealer.service;

import com.example.jsonprocessingcardealer.models.dtos.PresentCustomerDto;
import com.example.jsonprocessingcardealer.models.entities.Customer;

import java.io.IOException;
import java.util.List;

public interface CustomerService {
    void seedCustomers() throws IOException;

    Customer getRandom();

    List<PresentCustomerDto> findAllCustomersOrderedByBirthday();
}
