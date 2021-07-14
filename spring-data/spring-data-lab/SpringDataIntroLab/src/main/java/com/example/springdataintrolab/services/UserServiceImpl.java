package com.example.springdataintrolab.services;

import com.example.springdataintrolab.exceptions.UserExistsExeption;
import com.example.springdataintrolab.models.Account;
import com.example.springdataintrolab.models.User;
import com.example.springdataintrolab.repositories.AccountRepository;
import com.example.springdataintrolab.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    public UserServiceImpl(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public void registerUser(User user) throws UserExistsExeption {
        User userByName = userRepository.getUserByName(user.getName());
        if (userByName != null) {
            throw new UserExistsExeption();
        }
        userRepository.save(user);
        Account account = new Account();
        account.setBalance(BigDecimal.valueOf(1000));
        account.setUser(user);
        accountRepository.save(account);



    }
}
