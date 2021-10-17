package com.example.supermarket.repositories;

import com.example.supermarket.model.Town;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TownRepository extends JpaRepository<Town, Long> {
    Town findTownByName(String name);
}
