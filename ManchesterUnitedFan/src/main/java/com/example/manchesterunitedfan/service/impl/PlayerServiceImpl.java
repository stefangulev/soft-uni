package com.example.manchesterunitedfan.service.impl;

import com.example.manchesterunitedfan.model.view.PlayerCardView;
import com.example.manchesterunitedfan.model.view.PlayerDetailsView;
import com.example.manchesterunitedfan.repository.PlayerRepository;
import com.example.manchesterunitedfan.service.PlayerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {
    private final ModelMapper modelMapper;
    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(ModelMapper modelMapper, PlayerRepository playerRepository) {
        this.modelMapper = modelMapper;
        this.playerRepository = playerRepository;
    }

    @Override
    public List<PlayerCardView> findSquad() {
        return playerRepository.findAll().stream().map(p -> modelMapper.map(p, PlayerCardView.class))
                .collect(Collectors.toList());
    }

    @Override
    public PlayerDetailsView findPlayerById(Long id) {
        return modelMapper.map(playerRepository.findById(id).orElse(null), PlayerDetailsView.class);
    }
}
