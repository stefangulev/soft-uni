package com.example.jsonprocessingcardealer.repositories;

import com.example.jsonprocessingcardealer.models.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    List<Supplier> findAllByImporterIsFalse();
}
