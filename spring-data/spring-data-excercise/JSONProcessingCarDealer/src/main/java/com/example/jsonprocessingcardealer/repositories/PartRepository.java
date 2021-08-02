package com.example.jsonprocessingcardealer.repositories;

import com.example.jsonprocessingcardealer.models.entities.Part;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartRepository extends JpaRepository<Part, Long> {
}
