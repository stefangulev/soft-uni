package softuni.exam.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.domain.dtos.TeamSeedDto;
import softuni.exam.domain.entities.Team;
import softuni.exam.repository.PictureRepository;
import softuni.exam.repository.TeamRepository;
import softuni.exam.util.FileUtil;
import softuni.exam.util.ValidatorUtil;
import softuni.exam.util.XmlValidatorUtil;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {

    private final String XML_FILE_PATH = "src/main/resources/files/xml/teams.xml";
    private final TeamRepository teamRepository;
    private final ModelMapper modelMapper;
    private final XmlValidatorUtil xmlValidatorUtil;
    private final ValidatorUtil validatorUtil;
    private final FileUtil fileUtil;
    private final PictureRepository pictureRepository;

    public TeamServiceImpl(TeamRepository teamRepository, ModelMapper modelMapper, XmlValidatorUtil xmlValidatorUtil, ValidatorUtil validatorUtil, FileUtil fileUtil, PictureRepository pictureRepository) {
        this.teamRepository = teamRepository;
        this.modelMapper = modelMapper;
        this.xmlValidatorUtil = xmlValidatorUtil;
        this.validatorUtil = validatorUtil;
        this.fileUtil = fileUtil;
        this.pictureRepository = pictureRepository;
    }

    @Override
    public String importTeams() throws JAXBException, IOException {
        StringBuilder sb = new StringBuilder();
        TeamSeedDto teamSeedDto = xmlValidatorUtil.fromFile(XML_FILE_PATH, TeamSeedDto.class);
        teamSeedDto.getTeams().stream().filter(d -> {
            boolean isValid = validatorUtil.isValid(d);
            if (pictureRepository.findPictureByUrl(d.getPicture().getUrl()) == null) {
                isValid = false;
            }
            sb.append(isValid ? String.format("Successfully imported - %s", d.getName()) : "Invalid team").append(System.lineSeparator());
            return isValid;
        }).map(d -> {
            Team team = modelMapper.map(d, Team.class);
            team.setPicture(pictureRepository.findPictureByUrl(team.getPicture().getUrl()));
            return team;
        }).forEach(teamRepository::save);
        return sb.toString();
    }

    @Override
    public boolean areImported() {
        return teamRepository.count() > 0;
    }

    @Override
    public String readTeamsXmlFile() throws IOException {
        return fileUtil.readFile(XML_FILE_PATH);
    }
}
