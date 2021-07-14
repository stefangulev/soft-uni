package com.example.demo.service;

import com.example.demo.models.dtos.AddGameDto;

public interface GameService {
    void addGame(AddGameDto addGameDto);

    void deleteGame(long parseLong);

    void editGame(long parseLong, String s, String s1);
}
