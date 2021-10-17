package com.example.themarket.services;

import com.example.themarket.model.dtos.CreateUserServiceDto;

public interface UserService {
    boolean create(CreateUserServiceDto createUserServiceDto);
}
