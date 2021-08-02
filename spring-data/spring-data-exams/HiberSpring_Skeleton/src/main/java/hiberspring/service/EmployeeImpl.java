package hiberspring.service;

import hiberspring.domain.dtos.EmployeeSeedDto;
import hiberspring.domain.entities.Employee;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.EmployeeCardRepository;
import hiberspring.repository.EmployeeRepository;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import hiberspring.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static hiberspring.common.Constants.PATH_TO_FILES;

@Service
public class EmployeeImpl implements EmployeeService {
    private final String XML_FILE_NAME = "employees.xml";
    private final EmployeeRepository employeeRepository;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final FileUtil fileUtil;
    private final ModelMapper modelMapper;
    private final EmployeeCardRepository employeeCardRepository;
    private final BranchRepository branchRepository;

    public EmployeeImpl(EmployeeRepository employeeRepository, XmlParser xmlParser, ValidationUtil validationUtil, FileUtil fileUtil, ModelMapper modelMapper, EmployeeCardRepository employeeCardRepository, BranchRepository branchRepository) {
        this.employeeRepository = employeeRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.fileUtil = fileUtil;
        this.modelMapper = modelMapper;
        this.employeeCardRepository = employeeCardRepository;
        this.branchRepository = branchRepository;
    }

    @Override
    public Boolean employeesAreImported() {
        return employeeRepository.count() > 0;
    }

    @Override
    public String readEmployeesXmlFile() throws IOException {
        return fileUtil.readFile(PATH_TO_FILES + XML_FILE_NAME);
    }

    @Override
    public String importEmployees() throws JAXBException, FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        EmployeeSeedDto employeeSeedDto = xmlParser.parseXml(EmployeeSeedDto.class, PATH_TO_FILES + XML_FILE_NAME);
        employeeSeedDto.getEmployees().stream().filter(e -> {
            boolean isValid = validationUtil.isValid(e);
            if (employeeRepository.findEmployeeByEmployeeCard_Number(e.getCard()) != null) {
                isValid = false;
            }
            if(branchRepository.findBranchByName(e.getBranch()) == null) {
                isValid = false;
            }
            sb.append(isValid ? String.format("Successfully imported Employee %s %s.", e.getFirstName(), e.getLastName()) :
                    "Error: Invalid data.").append(System.lineSeparator());
            return isValid;
        }).map(d -> {
            Employee employee = modelMapper.map(d, Employee.class);
            employee.setEmployeeCard(employeeCardRepository.findEmployeeCardByNumber(d.getCard()));
            employee.setBranch(branchRepository.findBranchByName(d.getBranch()));
            return employee;
        }).forEach(employeeRepository::save);
        return sb.toString();
    }

    @Override
    public String exportProductiveEmployees() {
        StringBuilder sb = new StringBuilder();
employeeRepository.findEmployeesInBranchWithAtLeastOneProductOrderByFullNameAlphabeticallyLengthOfPositionDesc().stream()
.forEach(e -> {
    sb.append(String.format("Name: %s %s\nPosition: %s\nCard Number: %s\n -------------------------\n",
            e.getFirstName(), e.getLastName(), e.getPosition(), e.getEmployeeCard().getNumber()))
            .append(System.lineSeparator());
});

        return sb.toString();
    }
}
