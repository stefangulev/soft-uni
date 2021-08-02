package com.example.jsonprocessingcardealer.service;

import com.example.jsonprocessingcardealer.models.dtos.SupplierCatalogDto;
import com.example.jsonprocessingcardealer.models.entities.Supplier;

import java.io.IOException;
import java.util.List;

public interface SupplierService {
    void seedSuppliers() throws IOException;
    Supplier getRandom();

    List<SupplierCatalogDto> getLocalSuppliers();
}
