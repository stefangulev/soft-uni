package com.example.jsonprocessingcardealer.repositories;

import com.example.jsonprocessingcardealer.models.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
