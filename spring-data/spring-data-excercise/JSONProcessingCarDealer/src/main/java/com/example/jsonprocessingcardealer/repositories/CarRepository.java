package com.example.jsonprocessingcardealer.repositories;

import com.example.jsonprocessingcardealer.models.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> getCarsByMakeOrderByModelAscTravelledDistanceDesc(String model);
}
