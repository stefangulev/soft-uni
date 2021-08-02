package hiberspring.service;

import com.google.gson.Gson;
import hiberspring.domain.dtos.EmployeeCardSeedDto;
import hiberspring.domain.entities.EmployeeCard;
import hiberspring.repository.EmployeeCardRepository;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static hiberspring.common.Constants.PATH_TO_FILES;

@Service
public class EmployeeCardImpl implements EmployeeCardService{
    private final String FILE_NAME = "employee-cards.json";
    private final EmployeeCardRepository employeeCardRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final FileUtil fileUtil;

    public EmployeeCardImpl(EmployeeCardRepository employeeCardRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil, FileUtil fileUtil) {
        this.employeeCardRepository = employeeCardRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.fileUtil = fileUtil;
    }

    @Override
    public Boolean employeeCardsAreImported() { return employeeCardRepository.count() > 0; }

    @Override
    public String readEmployeeCardsJsonFile() throws IOException {
        return fileUtil.readFile(PATH_TO_FILES + FILE_NAME);
    }

    @Override
    public String importEmployeeCards(String employeeCardsFileContent) throws IOException {
        StringBuilder sb = new StringBuilder();

        Arrays.stream(gson.fromJson(readEmployeeCardsJsonFile(), EmployeeCardSeedDto[].class)).filter(d -> {
            boolean isValid = validationUtil.isValid(d);
            if (employeeCardRepository.findEmployeeCardByNumber(d.getNumber()) != null) {
                isValid = false;
            }
            sb.append(isValid ? String.format("Successfully imported Employee Card %s.", d.getNumber()) :
                    "Error: Invalid data.").append(System.lineSeparator());
            return isValid;
        }).map(d -> modelMapper.map(d, EmployeeCard.class)).forEach(employeeCardRepository::save);
        return sb.toString();
    }
}
