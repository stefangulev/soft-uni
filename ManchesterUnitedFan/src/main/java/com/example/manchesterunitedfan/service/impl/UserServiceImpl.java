package com.example.manchesterunitedfan.service.impl;

import com.example.manchesterunitedfan.model.entities.UserEntity;
import com.example.manchesterunitedfan.repository.UserRepository;
import com.example.manchesterunitedfan.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity findUserByUserName(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
}
