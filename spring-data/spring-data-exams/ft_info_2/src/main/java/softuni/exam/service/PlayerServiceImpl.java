package softuni.exam.service;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.domain.dtos.PlayerSeedDto;
import softuni.exam.domain.entities.Player;
import softuni.exam.repository.PictureRepository;
import softuni.exam.repository.PlayerRepository;
import softuni.exam.repository.TeamRepository;
import softuni.exam.util.FileUtil;
import softuni.exam.util.ValidatorUtil;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {
    private final String JSON_FILE_PATH = "src/main/resources/files/json/players.json";
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final PlayerRepository playerRepository;
    private final ValidatorUtil validatorUtil;
    private final FileUtil fileUtil;
    private final PictureRepository pictureRepository;
    private final TeamRepository teamRepository;

    public PlayerServiceImpl(Gson gson, ModelMapper modelMapper, PlayerRepository playerRepository, ValidatorUtil validatorUtil, FileUtil fileUtil, PictureRepository pictureRepository, TeamRepository teamRepository) {
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.playerRepository = playerRepository;
        this.validatorUtil = validatorUtil;
        this.fileUtil = fileUtil;
        this.pictureRepository = pictureRepository;
        this.teamRepository = teamRepository;
    }


    @Override
    public String importPlayers() throws IOException {
        StringBuilder sb = new StringBuilder();
       Arrays.stream(gson.fromJson(readPlayersJsonFile(), PlayerSeedDto[].class)).filter(d -> {
            boolean isValid = validatorUtil.isValid(d);
            if (pictureRepository.findPictureByUrl(d.getPicture().getUrl()) == null) {
                isValid = false;
            }
            if (teamRepository.findTeamByNameAndPicture_Url(d.getTeam().getName(), d.getTeam().getPicture().getUrl()) == null) {
                isValid = false;
            }
            sb.append(isValid ? String.format("Successfully imported player: %s %s", d.getFirstName(), d.getLastName())
                    : "Invalid player").append(System.lineSeparator());
            return isValid;
        }).map(d -> {
            Player player = modelMapper.map(d, Player.class);
            player.setPicture(pictureRepository.findPictureByUrl(d.getPicture().getUrl()));
            player.setTeam(teamRepository.findTeamByNameAndPicture_Url(d.getTeam().getName(), d.getTeam().getPicture().getUrl()));
            return player;
        }).forEach(playerRepository::save);

        return sb.toString();
    }

    @Override
    public boolean areImported() {
        return playerRepository.count() > 0;
    }

    @Override
    public String readPlayersJsonFile() throws IOException {
        return fileUtil.readFile(JSON_FILE_PATH);
    }

    @Override
    public String exportPlayersWhereSalaryBiggerThan() {
        StringBuilder sb = new StringBuilder();
        playerRepository.findAllBySalaryGreaterThanOrderBySalaryDesc(new BigDecimal(100000))
                .forEach(p -> {
                    sb.append(String.format("Player name: %s %s", p.getFirstName(), p.getLastName())).append(System.lineSeparator())
                            .append("\t" + String.format("Number: %d", p.getNumber())).append(System.lineSeparator())
                            .append("\t" + String.format("Salary: %s", p.getSalary())).append(System.lineSeparator())
                            .append("\t" + String.format("Team name: %s", p.getTeam().getName())).append(System.lineSeparator());
                });
        return sb.toString();
    }

    @Override
    public String exportPlayersInATeam() {
        StringBuilder sb = new StringBuilder();
        sb.append("Team: North Hub").append(System.lineSeparator());
        playerRepository.findAllByTeam_NameOrderById("North Hub").forEach(p -> {
            sb.append("\t" + String.format("Player name: %s %s - %s", p.getFirstName(), p.getLastName(), p.getPosition())).append(System.lineSeparator())
                    .append("\t" + String.format("Number: %d", p.getNumber())).append(System.lineSeparator());
        });
        return sb.toString();
    }
}
