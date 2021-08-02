package com.example.football.service.impl;

import com.example.football.models.dto.StatSeedDto;
import com.example.football.models.entity.Stat;
import com.example.football.repository.StatRepository;
import com.example.football.service.StatService;
import com.example.football.util.ValidationUtil;
import com.example.football.util.XmlParserUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatServiceImpl implements StatService {
    private final String STATS_FILE_PATH = "src/main/resources/files/xml/stats.xml";
    private final StatRepository statRepository;
    private final XmlParserUtil xmlParserUtil;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public StatServiceImpl(StatRepository statRepository, XmlParserUtil xmlParserUtil, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.statRepository = statRepository;
        this.xmlParserUtil = xmlParserUtil;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }


    @Override
    public boolean areImported() {
        return statRepository.count() > 0;
    }

    @Override
    public String readStatsFileContent() throws IOException {
        return Files.readString(Path.of(STATS_FILE_PATH));
    }

    @Override
    public String importStats() throws JAXBException, FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        StatSeedDto statSeedDto = xmlParserUtil.fromFile(STATS_FILE_PATH, StatSeedDto.class);
        statSeedDto.getStats().stream().filter(d -> {
            boolean isValid = validationUtil.isValid(d)
                    && statRepository.findStatByPassingAndShootingAndEndurance(d.getPassing(), d.getShooting(), d.getEndurance())
                    == null;
            sb.append(isValid ? String.format("Successfully imported Stat %.2f - %.2f - %.2f",
                    d.getShooting(), d.getPassing(), d.getEndurance()) : "Invalid Stat").append(System.lineSeparator());
            return isValid;
        }).map(d -> modelMapper.map(d, Stat.class)).forEach(statRepository::save);
        return sb.toString();
    }
}
