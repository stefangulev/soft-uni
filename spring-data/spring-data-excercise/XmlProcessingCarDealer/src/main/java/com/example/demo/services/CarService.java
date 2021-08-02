package com.example.demo.services;

import com.example.demo.models.dtos.CarSeedDto;
import com.example.demo.models.dtos.CarsByMakeDto;
import com.example.demo.models.dtos.CarsWithPartsDto;
import com.example.demo.models.entities.Car;

import java.util.List;

public interface CarService {
    void seedCars(CarSeedDto seedCarDto);
    Long getCount();

    Car getRandom();

    CarsByMakeDto getToyotaCarsOrderedByModelAndTravelledDistance();

    CarsWithPartsDto findCarsWithListOfParts();
}
