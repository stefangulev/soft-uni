package com.example.springdataintrolab.services;

import com.example.springdataintrolab.exceptions.UserExistsExeption;
import com.example.springdataintrolab.models.User;

public interface UserService {
    void registerUser(User user) throws UserExistsExeption;
}
