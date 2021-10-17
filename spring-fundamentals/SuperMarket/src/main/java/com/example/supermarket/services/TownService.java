package com.example.supermarket.services;

import com.example.supermarket.model.Town;

public interface TownService {
    String addTown(String townName);
    boolean isTownValid(Town town);

}
