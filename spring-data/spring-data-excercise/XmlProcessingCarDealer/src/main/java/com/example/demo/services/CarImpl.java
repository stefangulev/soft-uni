package com.example.demo.services;

import com.example.demo.models.dtos.*;
import com.example.demo.models.entities.Car;
import com.example.demo.repositories.CarRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class CarImpl implements CarService{

    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final PartService partService;

    public CarImpl(CarRepository carRepository, ModelMapper modelMapper, PartService partService) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
        this.partService = partService;
    }


    @Override
    public void seedCars(CarSeedDto seedCarDto) {
        seedCarDto.getCars().stream().map(d -> {
            Car car = modelMapper.map(d, Car.class);
            car.setParts(partService.getRandom());
            return car;
        }).forEach(carRepository::save);
    }

    @Override
    public Long getCount() {
        return carRepository.count();
    }

    @Override
    public Car getRandom() {
        long randomId = ThreadLocalRandom.current().nextLong(1, carRepository.count() + 1);
        return carRepository.findById(randomId).orElse(null);
    }

    @Override
    public CarsByMakeDto getToyotaCarsOrderedByModelAndTravelledDistance() {
        List<CarsByMakeSingleDto> toyotaList = carRepository.findCarsByMakeOrderByModelAscTravelledDistanceDesc("Toyota").stream()
                .map(c -> modelMapper.map(c, CarsByMakeSingleDto.class)).collect(Collectors.toList());
        CarsByMakeDto carsByMakeDto = new CarsByMakeDto();
        carsByMakeDto.setCars(toyotaList);
        return carsByMakeDto;
    }

    @Override
    public CarsWithPartsDto findCarsWithListOfParts() {
        List<CarsWithPartsSingleDto> carsWithParts = carRepository.findAll().stream().map(c -> modelMapper.map(c, CarsWithPartsSingleDto.class)).collect(Collectors.toList());
       CarsWithPartsDto carsWithPartsDto = new CarsWithPartsDto();
       carsWithPartsDto.setCars(carsWithParts);
        return carsWithPartsDto;
    }
}
