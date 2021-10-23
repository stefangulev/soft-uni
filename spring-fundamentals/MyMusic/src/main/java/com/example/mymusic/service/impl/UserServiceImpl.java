package com.example.mymusic.service.impl;

import com.example.mymusic.model.entities.User;
import com.example.mymusic.model.service.UserLoginServiceModel;
import com.example.mymusic.model.service.UserRegisterServiceModel;
import com.example.mymusic.repositories.UserRepository;
import com.example.mymusic.service.UserService;
import com.example.mymusic.user.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final CurrentUser currentUser;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, CurrentUser currentUser, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean userWithEmailOrUsernameExists(UserRegisterServiceModel userRegisterServiceModel) {
        return userRepository.findUserByUsernameOrEmail(userRegisterServiceModel.getUsername(), userRegisterServiceModel.getEmail())
                != null;
    }

    @Override
    public void register(UserRegisterServiceModel userRegisterServiceModel) {
        userRepository.save(modelMapper.map(userRegisterServiceModel, User.class));
    }

    @Override
    public boolean userWithUsernameAndPasswordExists(UserLoginServiceModel userLoginServiceModel) {
        return userRepository.findUserByUsernameAndPassword(userLoginServiceModel.getUsername(), userLoginServiceModel.getPassword())
                != null;
    }

    @Override
    public void login(UserLoginServiceModel userLoginServiceModel) {
        User userByUsernameAndPassword = userRepository.findUserByUsernameAndPassword(userLoginServiceModel.getUsername(), userLoginServiceModel.getPassword());
        currentUser.setId(userByUsernameAndPassword.getId()).setLoggedIn(true);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

}
