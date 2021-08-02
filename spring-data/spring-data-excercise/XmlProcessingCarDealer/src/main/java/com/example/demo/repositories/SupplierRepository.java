package com.example.demo.repositories;

import com.example.demo.models.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    List<Supplier> findAllByImporterIsFalse();
}
