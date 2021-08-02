package com.example.demo.services;

import com.example.demo.models.dtos.CustomerSeedDto;
import com.example.demo.models.dtos.OrderedCustomerDto;
import com.example.demo.models.dtos.TotalSalesByCustomerDto;
import com.example.demo.models.entities.Customer;

public interface CustomerService{
    void seedCustomers(CustomerSeedDto customerSeedDto);
    Long getCount();

    Customer getRandom();

    OrderedCustomerDto getOrderedCustomersByBirthDate();

    TotalSalesByCustomerDto getTotalSalesByCustomer();
}
