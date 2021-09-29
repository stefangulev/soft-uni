package com.example.themarket.services.impl;

import com.example.themarket.model.dtos.CreateUserServiceDto;
import com.example.themarket.model.entities.User;
import com.example.themarket.repositories.UserRepository;
import com.example.themarket.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean create(CreateUserServiceDto createUserServiceDto) {
       if (userRepository.findByUserName(createUserServiceDto.getUsername()) != null) {
           return false;
       }
       User user = new User().setUsername(createUserServiceDto.getUsername()).setAccountBalance(createUserServiceDto.getAccountBalance());
       userRepository.save(user);
       return true;
    }
}
