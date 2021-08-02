package com.example.jsonprocessing.services;

import com.example.jsonprocessing.models.dtos.UserCatalogueDto;
import com.example.jsonprocessing.models.dtos.UserSeedDto;
import com.example.jsonprocessing.models.dtos.UserWithMoreThanOneSoldItemDto;
import com.example.jsonprocessing.models.entities.User;
import com.example.jsonprocessing.repositories.UserRepository;
import com.example.jsonprocessing.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserImpl implements UserService{

    private final String PATH_NAME = "src/main/resources/files/users.json";
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final UserRepository userRepository;


    public UserImpl(ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.userRepository = userRepository;
    }


    @Override
    public void seedUsers() throws IOException {
        if(userRepository.count() >0) {
            return;
        }
        Arrays.stream(gson.fromJson(Files.readString(Path.of(PATH_NAME)), UserSeedDto[].class)).filter(validationUtil::isValid)
                .map(d -> modelMapper.map(d, User.class)).forEach(userRepository::save);



    }

    @Override
    public User findRandom() {
        long randomId = ThreadLocalRandom.current().nextLong(1, userRepository.count() + 1);
        return userRepository.findById(randomId).orElse(null);
    }

    @Override
    public List<UserWithMoreThanOneSoldItemDto> findUsersWithMoreThanOneSoldProductOrderByLastNameThenFirstName() {
        return userRepository.findUsersWithMoreThanOneSoldProductOrderByLastNameThenFirstName().stream().map(u -> modelMapper.map(u, UserWithMoreThanOneSoldItemDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<UserCatalogueDto> getCatalogueOfUsersAndSoldProducts() {
        return userRepository.findUsersWithMoreThanOneSoldProductOrderByProductSoldAndLastName()
                .stream().map(e -> modelMapper.map(e, UserCatalogueDto.class)).collect(Collectors.toList());
    }
}
