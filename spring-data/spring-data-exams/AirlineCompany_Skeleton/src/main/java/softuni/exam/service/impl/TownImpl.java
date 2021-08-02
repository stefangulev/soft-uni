package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.TownSeedDto;
import softuni.exam.models.entities.Town;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.TownService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TownImpl implements TownService {
    private final String JSON_PATH_NAME = "src/main/resources/files/json/towns.json";
    private final TownRepository townRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public TownImpl(TownRepository townRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
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
        return Files.readString(Path.of(JSON_PATH_NAME));
    }

    @Override
    public String importTowns() throws IOException {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(gson.fromJson(readTownsFileContent(), TownSeedDto[].class)).filter(d -> {
            boolean isValid = validationUtil.isValid(d);
            sb.append(isValid ? String.format("Successfully imported Town %s - %d", d.getName(), d.getPopulation()) :
                    "Invalid Town").append(System.lineSeparator());
            return isValid;
        }).map(d -> modelMapper.map(d, Town.class)).forEach(townRepository::save);

        return sb.toString();
    }
}
