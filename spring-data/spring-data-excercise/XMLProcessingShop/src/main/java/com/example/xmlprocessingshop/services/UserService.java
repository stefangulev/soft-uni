package com.example.xmlprocessingshop.services;

import com.example.xmlprocessingshop.models.dtos.UserSeedDto;
import com.example.xmlprocessingshop.models.dtos.UsersAndProductsDto;
import com.example.xmlprocessingshop.models.dtos.UsersWithAtLeastOneSoldDto;
import com.example.xmlprocessingshop.models.entities.User;

public interface UserService {
    void seedUsers(UserSeedDto userSeedDto);
    Long getCount();
    User findRandom();

    UsersWithAtLeastOneSoldDto getAllUsersWithAtLeastOneSoldProduct();

    UsersAndProductsDto getUsersAndProducts();
}
