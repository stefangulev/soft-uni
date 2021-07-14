package com.example.demo.service;

import com.example.demo.models.dtos.AddGameDto;
import com.example.demo.models.entities.Game;
import com.example.demo.models.entities.User;
import com.example.demo.repositories.GameRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

@Component
public class GameServiceImpl implements GameService{

    private final GameRepository gameRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final UserServiceImpl userService;


    public GameServiceImpl(GameRepository gameRepository, ModelMapper modelMapper, ValidationUtil validationUtil, UserServiceImpl userService) {
        this.gameRepository = gameRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.userService = userService;
    }


    @Override
    public void addGame(AddGameDto addGameDto) {
        if(!validateUser()) {
            return;
        }

        Set<ConstraintViolation<AddGameDto>> violation = validationUtil.violation(addGameDto);
        if (!violation.isEmpty()) {
            violation.stream().map(ConstraintViolation::getMessage).forEach(System.out::println);
            return;
        }
        Game game = modelMapper.map(addGameDto, Game.class);
        gameRepository.save(game);
        System.out.println(game.getTitle() + " added!");


    }
    @Override
    public void deleteGame(long id) {
        if(!validateUser()) {
            return;
        }
        Game game = gameRepository.findById(id).orElse(null);
        if (game == null) {
            System.out.printf("Game with id %d does not exist!%n", id);
        } else {
            gameRepository.deleteById(id);
            System.out.println("Deleted " + game.getTitle());
        }

    }

    @Override
    public void editGame(long id, String priceExpression, String sizeExpression) {
        if(!validateUser()) {
            return;
        }
        Game game = gameRepository.findById(id).orElse(null);
        if (game == null) {
            System.out.printf("No game with id %d was found!%n", id);
            return;
        }
        BigDecimal price = new BigDecimal(priceExpression.split("=")[1]);
        double size = Double.parseDouble(sizeExpression.split("=")[1]);
        game.setPrice(price);
        game.setSize((int) size);
        gameRepository.save(game);
        System.out.println("Edited " + game.getTitle());
    }

    private boolean validateUser() {
        User loggedIn = userService.getLoggedInUser();
        if(loggedIn == null) {
            System.out.println("No user is logged in.");
            return false;
        } else if (!loggedIn.isAdministrator()) {
            System.out.println("Logged in user is not an admin, and they cannot manipulate the game storage.");
            return false;
        }
        return true;
    }


}
