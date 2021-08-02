package hiberspring.service;

import com.google.gson.Gson;
import hiberspring.domain.dtos.TownSeedDto;
import hiberspring.domain.entities.Town;
import hiberspring.repository.TownRepository;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;

import static hiberspring.common.Constants.PATH_TO_FILES;

@Service
public class TownImpl implements TownService{

    private final String FILE_NAME = "towns.json";
    private final TownRepository townRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;

    public TownImpl(TownRepository townRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper, FileUtil fileUtil) {
        this.townRepository = townRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
    }

    @Override
    public Boolean townsAreImported() {
        return townRepository.count() > 0;
    }

    @Override
    public String readTownsJsonFile() throws IOException {
        return fileUtil.readFile(PATH_TO_FILES + FILE_NAME);
    }

    @Override
    public String importTowns(String townsFileContent) throws IOException {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(gson.fromJson(readTownsJsonFile(), TownSeedDto[].class)).filter(t -> {
            boolean isValid = validationUtil.isValid(t);
            sb.append( isValid ? String.format("Successfully imported Town %s.", t.getName()) : "Error: Invalid data")
                    .append(System.lineSeparator());
            return isValid;
        }).map(t -> modelMapper.map(t, Town.class)).forEach(townRepository::save);
        return sb.toString();
    }
}
