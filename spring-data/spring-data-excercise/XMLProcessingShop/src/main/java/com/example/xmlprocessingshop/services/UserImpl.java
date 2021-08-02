package com.example.xmlprocessingshop.services;

import com.example.xmlprocessingshop.models.dtos.*;
import com.example.xmlprocessingshop.models.entities.User;
import com.example.xmlprocessingshop.repositories.UserRepository;
import com.example.xmlprocessingshop.util.ValidationImpl;
import com.example.xmlprocessingshop.util.XMLConvertor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class UserImpl implements UserService{

    private final ModelMapper modelMapper;
    private final ValidationImpl validation;
    private final UserRepository userRepository;

    public UserImpl(ModelMapper modelMapper, ValidationImpl validation, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.validation = validation;
        this.userRepository = userRepository;
    }

    @Override
    public void seedUsers(UserSeedDto userSeedDto) {
        userSeedDto.getUsers().stream().filter(validation::isValid).map(d -> modelMapper.map(d, User.class)).forEach(userRepository::save);
    }

    @Override
    public Long getCount() {
        return userRepository.count();
    }

    @Override
    public User findRandom() {
        long randomId = ThreadLocalRandom.current().nextLong(1, userRepository.count() + 1);
        return userRepository.findById(randomId).orElse(null);
    }

    @Override
    public UsersWithAtLeastOneSoldDto getAllUsersWithAtLeastOneSoldProduct() {
        List<UsersWithAtLeastOneSoldSingleDto> collect = userRepository.findUsersWithMoreThanOneSoldProductOrderByLastNameThenFirstName().stream().map(u -> modelMapper.map(u, UsersWithAtLeastOneSoldSingleDto.class)).collect(Collectors.toList());
        UsersWithAtLeastOneSoldDto usersWithAtLeastOneSoldDto = new UsersWithAtLeastOneSoldDto();
        usersWithAtLeastOneSoldDto.setUsers(collect);
        return usersWithAtLeastOneSoldDto;
    }

    @Override
    public UsersAndProductsDto getUsersAndProducts() {
        List<UserAndProductsSingleDto> collect = userRepository.findUsersWithMoreThanOneSoldProductOrderByProductSoldAndLastName().stream().map(u -> {
            UserAndProductsSingleDto map = modelMapper.map(u, UserAndProductsSingleDto.class);
            map.setProductCount((long) map.getSoldProducts().size());
            return map;
        }).collect(Collectors.toList());

        UsersAndProductsDto usersAndProductsDto = new UsersAndProductsDto();
        usersAndProductsDto.setUsers(collect);
        usersAndProductsDto.setCount((long) collect.size());
        return usersAndProductsDto;

    }
}
