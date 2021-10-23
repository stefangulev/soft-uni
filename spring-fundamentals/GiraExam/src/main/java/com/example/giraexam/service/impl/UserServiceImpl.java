package com.example.giraexam.service.impl;

import com.example.giraexam.model.entities.User;
import com.example.giraexam.model.service.UserLoginServiceModel;
import com.example.giraexam.model.service.UserRegisterServiceModel;
import com.example.giraexam.repository.UserRepository;
import com.example.giraexam.service.UserService;
import com.example.giraexam.user.CurrentUser;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
    }


    @Override
    public boolean userWithUsernameOrEmailExists(UserRegisterServiceModel userRegisterServiceModel) {
        return userRepository.findUserByUsernameOrEmail(userRegisterServiceModel.getUsername(), userRegisterServiceModel.getEmail())
                != null;
    }

    @Override
    public boolean userWithEmailOrPasswordExists(UserLoginServiceModel userLoginServiceModel) {
        return userRepository.findUserByEmailAndPassword(userLoginServiceModel.getEmail(), userLoginServiceModel.getPassword())
                != null;
    }

    @Override
    public void register(UserRegisterServiceModel userRegisterServiceModel) {
        User user = new User().setUsername(userRegisterServiceModel.getUsername())
                .setEmail(userRegisterServiceModel.getEmail()).setPassword(userRegisterServiceModel.getPassword());
        userRepository.save(user);
    }

    @Override
    public void login(UserLoginServiceModel serviceModel) {
        User userByEmailOrPassword = userRepository.findUserByEmailAndPassword(serviceModel.getEmail(), serviceModel.getPassword());
        currentUser.setId(userByEmailOrPassword.getId()).setLoggedIn(true);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
