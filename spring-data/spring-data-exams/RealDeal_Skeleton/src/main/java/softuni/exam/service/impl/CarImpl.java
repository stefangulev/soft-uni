package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.Car;
import softuni.exam.models.dtos.CarSeedDto;
import softuni.exam.repository.CarRepository;
import softuni.exam.service.CarService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarImpl implements CarService {

    private final String JSON_PATH_NAME = "src/main/resources/files/json/cars.json";
    private final CarRepository carRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public CarImpl(CarRepository carRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }


    @Override
    public boolean areImported() {
        return carRepository.count() > 0;
    }

    @Override
    public String readCarsFileContent() throws IOException {
        return Files.readString(Path.of(JSON_PATH_NAME));
    }

    @Override
    public String importCars() throws IOException {
        StringBuilder sb = new StringBuilder();

        Arrays.stream(gson.fromJson(readCarsFileContent(), CarSeedDto[].class)).filter(r -> {
            boolean isValid = validationUtil.isValid(r);
            sb.append(isValid ? String.format("Successfully imported car - %s - %s%n", r.getMake(),
                    r.getModel()) : String.format("Invalid car%n"));
            return isValid;
        }).map(c -> {
            Car car = new Car();
            car.setMake(c.getMake());
            car.setModel(c.getModel());
            car.setKilometers(c.getKilometers());
            car.setRegisteredOn(LocalDate.parse(c.getRegisteredOn(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            return car;
        }).forEach(carRepository::save);
        return sb.toString();
    }

    @Override
    public String getCarsOrderByPicturesCountThenByMake() {
        StringBuilder sb = new StringBuilder();
       carRepository.findCarsOrderedByPictureCountAndMake().forEach(c -> {
           sb.append(String.format("Car make - %s, model - %s", c.getMake(), c.getModel())).append(System.lineSeparator()).append("\t").append(String.format("Kilometers - %d", c.getKilometers())).append(System.lineSeparator()).append("\t").append(String.format("Registered on - %s", c.getRegisteredOn())).append(System.lineSeparator()).append("\t").append(String.format("Number of pictures - %d", c.getPictures().size())).append(System.lineSeparator());
       });
        return sb.toString();
    }
}
