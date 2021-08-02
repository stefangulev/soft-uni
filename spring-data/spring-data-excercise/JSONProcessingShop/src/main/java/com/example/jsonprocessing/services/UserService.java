package com.example.jsonprocessing.services;

import com.example.jsonprocessing.models.dtos.UserCatalogueDto;
import com.example.jsonprocessing.models.dtos.UserWithMoreThanOneSoldItemDto;
import com.example.jsonprocessing.models.entities.User;

import java.io.IOException;
import java.util.List;

public interface UserService {
    void seedUsers() throws IOException;
    User findRandom();
    List<UserWithMoreThanOneSoldItemDto> findUsersWithMoreThanOneSoldProductOrderByLastNameThenFirstName();

    List<UserCatalogueDto> getCatalogueOfUsersAndSoldProducts();
}
