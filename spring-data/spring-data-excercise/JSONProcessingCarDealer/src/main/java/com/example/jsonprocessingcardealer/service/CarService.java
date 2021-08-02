package com.example.jsonprocessingcardealer.service;

import com.example.jsonprocessingcardealer.models.dtos.CarCatalogeDto;
import com.example.jsonprocessingcardealer.models.dtos.CarWithPartsDto;
import com.example.jsonprocessingcardealer.models.entities.Car;

import java.io.IOException;
import java.util.List;

public interface CarService {
    void seedCars() throws IOException;

    Car getRandom();

    List<CarCatalogeDto> getToyotaCarsOrderByModelAndTravelDistance();

    List<CarWithPartsDto> getCarsAndListOfParts();
}
