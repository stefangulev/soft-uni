package com.example.football.service.impl;

import com.example.football.models.dto.PlayerSeedDto;
import com.example.football.models.entity.Player;
import com.example.football.repository.PlayerRepository;
import com.example.football.repository.StatRepository;
import com.example.football.repository.TeamRepository;
import com.example.football.repository.TownRepository;
import com.example.football.service.PlayerService;
import com.example.football.util.ValidationUtil;
import com.example.football.util.XmlParserUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.swing.text.DateFormatter;
import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {
    private final String PLAYERS_FILE_PATH = "src/main/resources/files/xml/players.xml";
    private final PlayerRepository playerRepository;
    private final XmlParserUtil xmlParserUtil;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final TownRepository townRepository;
    private final TeamRepository teamRepository;
    private final StatRepository statRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository, XmlParserUtil xmlParserUtil, ValidationUtil validationUtil, ModelMapper modelMapper, TownRepository townRepository, TeamRepository teamRepository, StatRepository statRepository) {
        this.playerRepository = playerRepository;
        this.xmlParserUtil = xmlParserUtil;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.townRepository = townRepository;
        this.teamRepository = teamRepository;
        this.statRepository = statRepository;
    }


    @Override
    public boolean areImported() {
        return playerRepository.count() > 0;
    }

    @Override
    public String readPlayersFileContent() throws IOException {
        return Files.readString(Path.of(PLAYERS_FILE_PATH));
    }

    @Override
    public String importPlayers() throws JAXBException, FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        PlayerSeedDto playerSeedDto = xmlParserUtil.fromFile(PLAYERS_FILE_PATH, PlayerSeedDto.class);
         playerSeedDto.getPlayers().stream().filter(d -> {
            boolean isValid = validationUtil.isValid(d) && playerRepository.findPlayerByEmail(d.getEmail()) == null
                    && statRepository.findById(d.getStat().getId()).orElse(null) != null;
            sb.append(isValid ?
                    String.format("Successfully imported Player %s %s - %s", d.getFirstName(), d.getLastName(), d.getPosition())
                    : "Invalid Player").append(System.lineSeparator());
            return isValid;
        }).map(d -> {
            Player player = modelMapper.map(d, Player.class);
            player.setBirthDate(LocalDate.parse(d.getBirthDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            player.setTown(townRepository.findTownByName(d.getTown().getName()));
            player.setTeam(teamRepository.findTeamByName(d.getTeam().getName()));
            player.setStat(statRepository.findById(d.getStat().getId()).orElse(null));
            return player;
        }).forEach(playerRepository::save);
        return sb.toString();
    }

    @Override
    public String exportBestPlayers() {
        StringBuilder sb = new StringBuilder();
        playerRepository.findAllByBirthDateAfterAndBirthDateBeforeOrdOrderByStat
                (LocalDate.parse("01-01-1995", DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                        LocalDate.parse("01-01-2003", DateTimeFormatter.ofPattern("dd-MM-yyyy")))
        .forEach(p -> {
            sb.append(String.format("Player - %s %s%n" +
                    "\t Position - %s%n" +
                    "\t Team - %s%n" +
                    "\t Stadium - %s%n", p.getFirstName(), p.getLastName(), p.getPosition(),
                    p.getTeam().getName(), p.getTeam().getStadiumName()));
        });
        return sb.toString();
    }
}
