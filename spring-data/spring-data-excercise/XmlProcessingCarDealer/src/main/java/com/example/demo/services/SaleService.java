package com.example.demo.services;


import com.example.demo.models.dtos.SaleDto;
import com.example.demo.models.dtos.SaleSeedDto;

public interface SaleService {
    void seedSales();
    Long getCount();

    SaleDto getSalesWithAppliedDiscount();
}
