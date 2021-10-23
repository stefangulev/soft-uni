package com.example.giraexam.service;

import com.example.giraexam.model.entities.User;
import com.example.giraexam.model.service.UserLoginServiceModel;
import com.example.giraexam.model.service.UserRegisterServiceModel;

public interface UserService {
    boolean userWithUsernameOrEmailExists(UserRegisterServiceModel userRegisterServiceModel);
    boolean userWithEmailOrPasswordExists(UserLoginServiceModel userLoginServiceModel);
    void register(UserRegisterServiceModel userRegisterServiceModel);
    void login(UserLoginServiceModel serviceModel);
    User findUserById(Long id);
}
