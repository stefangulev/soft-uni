package com.example.manchesterunitedfan.service;

import com.example.manchesterunitedfan.model.view.PlayerCardView;
import com.example.manchesterunitedfan.model.view.PlayerDetailsView;

import java.util.List;

public interface PlayerService {
    List<PlayerCardView> findSquad();
    PlayerDetailsView findPlayerById(Long id);
}
