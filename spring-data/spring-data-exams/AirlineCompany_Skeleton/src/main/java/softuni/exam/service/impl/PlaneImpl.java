package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.PlaneSeedDto;
import softuni.exam.models.entities.Plane;
import softuni.exam.repository.PlaneRepository;
import softuni.exam.service.PlaneService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlaneImpl implements PlaneService {
    private final String XML_FILE_PATH = "src/main/resources/files/xml/planes.xml";
    private final PlaneRepository planeRepository;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public PlaneImpl(PlaneRepository planeRepository, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.planeRepository = planeRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return planeRepository.count() > 0;
    }

    @Override
    public String readPlanesFileContent() throws IOException {
        return Files.readString(Path.of(XML_FILE_PATH));
    }

    @Override
    public String importPlanes() throws JAXBException, FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        PlaneSeedDto planeSeedDto = xmlParser.fromFile(XML_FILE_PATH, PlaneSeedDto.class);
       planeSeedDto.getPlanes().stream().filter(d -> {
            boolean isValid = validationUtil.isValid(d);
            sb.append(isValid ? String.format("Successfully imported Plane %s", d.getRegisterNumber()) :
                    "Invalid Plane").append(System.lineSeparator());
            return isValid;
        }).map(d -> modelMapper.map(d, Plane.class)).forEach(planeRepository::save);
        return sb.toString();
    }
}
