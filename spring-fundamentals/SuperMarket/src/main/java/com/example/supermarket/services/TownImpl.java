package com.example.supermarket.services;

import com.example.supermarket.model.Town;
import com.example.supermarket.repositories.TownRepository;
import org.springframework.stereotype.Service;

import javax.validation.Validator;

@Service
public class TownImpl implements TownService{
    private final TownRepository townRepository;
    private final Validator validator;

    public TownImpl(TownRepository townRepository, Validator validator) {
        this.townRepository = townRepository;
        this.validator = validator;
    }

    @Override
    public String addTown(String townName) {
        Town town = new Town(townName);
        if (!isTownValid(town)) {
            return "No name was entered!";
        }

        townRepository.save(town);
        return "Successfully added town!";
    }

    @Override
    public boolean isTownValid(Town town) {
        return validator.validate(town).isEmpty();
    }
}
