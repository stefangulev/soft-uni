package com.example.football.service.impl;

import com.example.football.models.dto.TownSeedDto;
import com.example.football.models.entity.Town;
import com.example.football.repository.TownRepository;
import com.example.football.service.TownService;
import com.example.football.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TownServiceImpl implements TownService {

    private final String TOWNS_FILE_PATH = "src/main/resources/files/json/towns.json";
    private final TownRepository townRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public TownServiceImpl(TownRepository townRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.townRepository = townRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }


    @Override
    public boolean areImported() {
        return townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return Files.readString(Path.of(TOWNS_FILE_PATH));
    }

    @Override
    public String importTowns() throws IOException {
        StringBuilder sb = new StringBuilder();
      Arrays.stream(gson.fromJson(readTownsFileContent(), TownSeedDto[].class)).filter(d -> {
            boolean isValid = validationUtil.isValid(d) && townRepository.findTownByName(d.getName()) == null;
            sb.append(isValid ? String.format("Successfully imported Town %s - %d", d.getName(), d.getPopulation())
                    : "Invalid Town").append(System.lineSeparator());
            return isValid;
        }).map(d -> modelMapper.map(d, Town.class)).forEach(townRepository::save);
        return sb.toString();
    }
}
