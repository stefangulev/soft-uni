package com.example.manchesterunitedfan.service;

import com.example.manchesterunitedfan.model.service.AddPlayerServiceModel;
import com.example.manchesterunitedfan.model.service.EditPlayerServiceModel;
import com.example.manchesterunitedfan.model.view.PlayerCardView;
import com.example.manchesterunitedfan.model.view.PlayerDetailsView;

import java.util.List;

public interface PlayerService {
    List<PlayerCardView> findSquad();
    PlayerDetailsView findPlayerById(Long id);

    boolean squadNumberTaken(Integer squadNumber);
    boolean squadNumberTaken(Integer squadNumber, Long id);

    void addPlayer(AddPlayerServiceModel serviceModel);

    void editPlayer(EditPlayerServiceModel serviceModel);

    void deletePlayer(Long id);
}
