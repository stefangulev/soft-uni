package com.example.springdataintrolab.services;

import com.example.springdataintrolab.models.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ConsoleRunner implements CommandLineRunner {
    private UserService userService;
    private AccountService accountService;

    public ConsoleRunner(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void run(String... args) throws Exception {
//        User user = new User();
//        user.setName("Pesho");
//        user.setAge(25);
//        userService.registerUser(user);
//        accountService.addMoney(BigDecimal.valueOf(155),2);
//        accountService.withdrawMoney(BigDecimal.valueOf(1000), 2);
        User user = new User();
        user.setName("Pesho");
        userService.registerUser(user);
    }
}
