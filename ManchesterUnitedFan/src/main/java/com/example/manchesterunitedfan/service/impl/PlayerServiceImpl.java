package com.example.manchesterunitedfan.service.impl;

import com.example.manchesterunitedfan.model.entities.PlayerEntity;
import com.example.manchesterunitedfan.model.service.AddPlayerServiceModel;
import com.example.manchesterunitedfan.model.service.EditPlayerServiceModel;
import com.example.manchesterunitedfan.model.view.PlayerCardView;
import com.example.manchesterunitedfan.model.view.PlayerDetailsView;
import com.example.manchesterunitedfan.repository.PlayerRepository;
import com.example.manchesterunitedfan.service.PlayerService;
import com.example.manchesterunitedfan.service.exceptions.PlayerNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        return modelMapper.map(playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException("Player with id " + id + " not found!")), PlayerDetailsView.class);
    }

    @Override
    public boolean squadNumberTaken(Integer squadNumber) {
        return playerRepository.findPlayerEntityBySquadNumber(squadNumber).isPresent();
    }

    @Override
    public boolean squadNumberTaken(Integer squadNumber, Long id) {
        Optional<PlayerEntity> playerEntityBySquadNumber = playerRepository.findPlayerEntityBySquadNumber(squadNumber);
        if(playerEntityBySquadNumber.isPresent()) {
            return !playerEntityBySquadNumber.get().getId().equals(id);
        } else {
            return false;
        }
    }


    @Override
    public void addPlayer(AddPlayerServiceModel serviceModel) {
        playerRepository.save(modelMapper.map(serviceModel, PlayerEntity.class));
    }

    @Override
    public void editPlayer(EditPlayerServiceModel serviceModel) {
        PlayerEntity playerEntity = playerRepository.findById(serviceModel.getId())
                .orElseThrow(() -> new PlayerNotFoundException("Player with id " + serviceModel.getId() + " not found!"));
        playerEntity.setFirstName(serviceModel.getFirstName())
                .setLastName(serviceModel.getLastName())
                .setAge(serviceModel.getAge())
                .setImgUrl(serviceModel.getImgUrl())
                .setDescription(serviceModel.getDescription())
                .setSquadNumber(serviceModel.getSquadNumber())
                .setNationality(serviceModel.getNationality())
                .setPosition(serviceModel.getPosition());
        playerRepository.save(playerEntity);
    }

    @Override
    public void deletePlayer(Long id) {
        playerRepository.deleteById(id);
    }
}
