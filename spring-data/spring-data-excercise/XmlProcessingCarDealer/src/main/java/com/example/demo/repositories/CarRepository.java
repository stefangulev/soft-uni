package com.example.demo.repositories;

import com.example.demo.models.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findCarsByMakeOrderByModelAscTravelledDistanceDesc(String model);
}
