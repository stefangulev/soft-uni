package com.example.jsonprocessingcardealer.service;

import com.example.jsonprocessingcardealer.models.dtos.CarCatalogeDto;
import com.example.jsonprocessingcardealer.models.dtos.CarSeedDto;
import com.example.jsonprocessingcardealer.models.dtos.CarWithPartsDto;
import com.example.jsonprocessingcardealer.models.entities.Car;
import com.example.jsonprocessingcardealer.repositories.CarRepository;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class CarImpl implements CarService{

    private final String FILE_PATH = "src/main/resources/files/cars.json";
    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final PartService partService;


    public CarImpl(CarRepository carRepository, ModelMapper modelMapper, Gson gson, PartService partService) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.partService = partService;
    }


    @Override
    public void seedCars() throws IOException {
        if(carRepository.count() > 0) {
            return;
        }
       Arrays.stream(gson.fromJson(Files.readString(Path.of(FILE_PATH)), CarSeedDto[].class))
                .map(d ->{
                    Car car = modelMapper.map(d, Car.class);
                    car.setParts(partService.getRandom());
                    return car;
                }).forEach(carRepository::save);
    }

    @Override
    public Car getRandom() {
        long randomId = ThreadLocalRandom.current().nextLong(1, carRepository.count() + 1);
       return carRepository.findById(randomId).orElse(null);
    }

    @Override
    public List<CarCatalogeDto> getToyotaCarsOrderByModelAndTravelDistance() {
        return carRepository.getCarsByMakeOrderByModelAscTravelledDistanceDesc("Toyota").stream()
                .map(c -> modelMapper.map(c, CarCatalogeDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<CarWithPartsDto> getCarsAndListOfParts() {
       return carRepository.findAll().stream()
                .map(c -> modelMapper.map(c, CarWithPartsDto.class)).collect(Collectors.toList());
    }
}
