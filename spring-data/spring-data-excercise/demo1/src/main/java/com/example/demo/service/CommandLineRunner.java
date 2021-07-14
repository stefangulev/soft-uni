package com.example.demo.service;

import com.example.demo.models.dtos.AddGameDto;
import com.example.demo.models.dtos.LoginUserDto;
import com.example.demo.models.dtos.RegisterUserDto;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class CommandLineRunner implements org.springframework.boot.CommandLineRunner {

    private final BufferedReader bufferedReader;
    private final UserService userService;
    private final GameService gameService;

    public CommandLineRunner(BufferedReader bufferedReader, UserService userService, GameService gameService) {
        this.bufferedReader = bufferedReader;
        this.userService = userService;
        this.gameService = gameService;
    }

    @Override
    public void run(String... args) throws Exception {
        while (true) {
            System.out.println("Enter command");
            String[] input = bufferedReader.readLine().split("\\|");

            switch (input[0]) {
                case "RegisterUser": userService.registerUser(new RegisterUserDto(input[1], input[2], input[3], input[4]));
                break;
                case "LoginUser": userService.loginUser(new LoginUserDto(input[1], input[2]));
                break;
                case "Logout": userService.logout();
                break;
                case "AddGame": gameService.addGame(new AddGameDto(input[1], new BigDecimal(input[2]), Double.parseDouble(input[3]), input[4], input[5], input[6], LocalDate.parse(input[7], DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
                break;
                case "EditGame": gameService.editGame(Long.parseLong(input[1]), input[2], input[3]);
                break;
                case "DeleteGame": gameService.deleteGame(Long.parseLong(input[1]));
                break;
                default:
                    System.out.println("Invalid command");
            }
        }
    }
}
