package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.PassengerSeedDto;
import softuni.exam.models.entities.Passenger;
import softuni.exam.repository.PassengerRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.PassengerService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PassengerImpl implements PassengerService {
    private final String JSON_FILE_PATH = "src/main/resources/files/json/passengers.json";
    private final PassengerRepository passengerRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final TownRepository townRepository;
    private final ModelMapper modelMapper;

    public PassengerImpl(PassengerRepository passengerRepository, Gson gson, ValidationUtil validationUtil, TownRepository townRepository, ModelMapper modelMapper) {
        this.passengerRepository = passengerRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return passengerRepository.count() > 0;
    }

    @Override
    public String readPassengersFileContent() throws IOException {
        return Files.readString(Path.of(JSON_FILE_PATH));
    }

    @Override
    public String importPassengers() throws IOException {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(gson.fromJson(readPassengersFileContent(), PassengerSeedDto[].class)).filter(d -> {
            boolean isValid = validationUtil.isValid(d);
            sb.append(isValid ? String.format("Successfully imported Passenger %s - %s", d.getLastName(), d.getEmail()) :
                    "Invalid Passenger").append(System.lineSeparator());
            return isValid;
        }).map(d -> {
            Passenger passenger = modelMapper.map(d, Passenger.class);
            passenger.setTown(townRepository.findTownByName(d.getTown()));
            return passenger;
        }).forEach(passengerRepository::save);
        return sb.toString();
    }

    @Override
    public String getPassengersOrderByTicketsCountDescendingThenByEmail() {
        StringBuilder sb = new StringBuilder();
       passengerRepository.findPassengersOrderByTicketsCountDescendingThenByEmail().forEach(p -> {
           sb.append(String.format("Passenger %s %s%n" +
                   "\tEmail - %s%n" +
                   "\tPhone - %s%n" +
                   "\tNumber of tickets - %d%n", p.getFirstName(),
                   p.getLastName(),
                   p.getEmail(),
                   p.getPhoneNumber(),
                   p.getTickets().size()
           )).append(System.lineSeparator());
       });

        return sb.toString();
    }
}
