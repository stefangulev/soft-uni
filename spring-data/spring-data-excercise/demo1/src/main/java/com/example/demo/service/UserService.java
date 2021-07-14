package com.example.demo.service;

import com.example.demo.models.dtos.LoginUserDto;
import com.example.demo.models.dtos.RegisterUserDto;

public interface UserService {
    void registerUser(RegisterUserDto registerUserDto);

    void loginUser(LoginUserDto loginUserDto);

    void logout();
}
