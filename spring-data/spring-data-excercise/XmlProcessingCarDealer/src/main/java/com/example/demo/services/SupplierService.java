package com.example.demo.services;

import com.example.demo.models.dtos.LocalSupplierDto;
import com.example.demo.models.dtos.SupplierSeedDto;
import com.example.demo.models.entities.Supplier;

public interface SupplierService {
    void seedSuppliers(SupplierSeedDto supplierSeedDto);
    Long getCount();
    Supplier getRandom();

    LocalSupplierDto findLocalSuppliers();
}
