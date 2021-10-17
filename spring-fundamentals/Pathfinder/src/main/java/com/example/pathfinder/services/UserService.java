package com.example.pathfinder.services;

import com.example.pathfinder.model.service.UserLoginServiceModel;
import com.example.pathfinder.model.service.UserRegisterServiceModel;
import com.example.pathfinder.model.view.ProfileViewModel;

public interface UserService {
    void register(UserRegisterServiceModel userRegisterServiceModel);
    boolean userExists(UserRegisterServiceModel userRegisterServiceModel);
    boolean loginPasswordCombinationValid(UserLoginServiceModel userLoginServiceModel);
    void login(UserLoginServiceModel userLoginServiceModel);
    void logout();
    ProfileViewModel showProfile(Long id);

}
