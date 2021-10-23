package com.example.mymusic.service;

import com.example.mymusic.model.entities.User;
import com.example.mymusic.model.service.UserLoginServiceModel;
import com.example.mymusic.model.service.UserRegisterServiceModel;

public interface UserService {
    boolean userWithEmailOrUsernameExists(UserRegisterServiceModel userRegisterServiceModel);

    void register(UserRegisterServiceModel userRegisterServiceModel);
    boolean userWithUsernameAndPasswordExists(UserLoginServiceModel userLoginServiceModel);

    void login(UserLoginServiceModel userLoginServiceModel);
    User findUserById(Long id);
}
