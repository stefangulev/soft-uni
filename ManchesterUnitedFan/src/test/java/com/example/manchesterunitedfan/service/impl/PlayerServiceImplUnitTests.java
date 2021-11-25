package com.example.manchesterunitedfan.service.impl;

import com.example.manchesterunitedfan.model.entities.PlayerEntity;
import com.example.manchesterunitedfan.model.view.PlayerCardView;
import com.example.manchesterunitedfan.repository.PlayerRepository;
import com.example.manchesterunitedfan.service.exceptions.PlayerNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceImplUnitTests {

    @Mock
    private PlayerRepository mockPlayerRepository;
    private PlayerServiceImpl playerService;
    private PlayerEntity testPlayer;
    private ModelMapper modelMapper = new ModelMapper();

    @BeforeEach
    void init() {
        testPlayer = new PlayerEntity().setLastName("Testov").setSquadNumber(7);
        playerService = new PlayerServiceImpl(modelMapper,mockPlayerRepository);
    }

    @Test
    void playerNotFound() {
        Assertions.assertThrows(PlayerNotFoundException.class, () ->
                playerService.findPlayerById(testPlayer.getId()));
    }

    @Test
    void playerFound() {
        Mockito.when(mockPlayerRepository.findById(testPlayer.getId()))
                .thenReturn(Optional.of(testPlayer));
        var actual = playerService.findPlayerById(testPlayer.getId());
        Assertions.assertEquals(actual.getLastName(), testPlayer.getLastName());
        Assertions.assertEquals(actual.getSquadNumber(), testPlayer.getSquadNumber());
    }

    @Test
    void getAllPlayersNotAdded() {
        List<PlayerCardView> squad = playerService.findSquad();
        Assertions.assertTrue(squad.isEmpty());
    }

    @Test
    void getAllPlayersAdded(){
        PlayerEntity testPlayer2 = new PlayerEntity().setLastName("Second").setSquadNumber(9);
        Mockito.when(mockPlayerRepository.findAll()).thenReturn(List.of(testPlayer, testPlayer2));
        var squad = playerService.findSquad();
        Assertions.assertEquals(squad.size(), 2);
        String playerNames = squad.stream().map(PlayerCardView::getLastName).collect(Collectors.joining(", "));
        Assertions.assertEquals(playerNames, "Testov, Second");
    }


}
