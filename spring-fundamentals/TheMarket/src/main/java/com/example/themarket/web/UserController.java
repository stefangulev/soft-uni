package com.example.themarket.web;

import com.example.themarket.model.dtos.CreateUserControllerDto;
import com.example.themarket.model.dtos.CreateUserServiceDto;
import com.example.themarket.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public boolean create(CreateUserControllerDto createUserControllerDto) {
        String inputUsername = createUserControllerDto.getUsername();
        BigDecimal accountBalance = createUserControllerDto.getAccountBalance();
        if(inputUsername.trim().length() < 1 || accountBalance == null) {
            LOGGER.info("Request to create user was registered, but failed. No username and/or account balance was entered!");
            return false;
        }
        if(accountBalance.doubleValue() < 0) {
            LOGGER.info("Request to create user was registered, but failed. Account balance cannot be negative!");
            return false;
        }
        boolean creationSuccessful = userService.create(new CreateUserServiceDto().setUsername(inputUsername).setAccountBalance(accountBalance));
        if (creationSuccessful) {
            LOGGER.info("User with username {} created in the database! Their account balance is {}!", inputUsername, accountBalance);
        } else {
            LOGGER.info("User with username {} already exists in the database!", inputUsername);
        }
        return creationSuccessful;
    }


}
