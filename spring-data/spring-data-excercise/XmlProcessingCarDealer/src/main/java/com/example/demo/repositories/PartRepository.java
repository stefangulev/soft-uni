package com.example.demo.repositories;

import com.example.demo.models.entities.Part;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartRepository extends JpaRepository<Part, Long> {
}
