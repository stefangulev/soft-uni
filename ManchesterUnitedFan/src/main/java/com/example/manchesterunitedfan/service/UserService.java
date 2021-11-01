package com.example.manchesterunitedfan.service;

import com.example.manchesterunitedfan.model.entities.UserEntity;

public interface UserService {
    UserEntity findUserByUserName(String username);
}
