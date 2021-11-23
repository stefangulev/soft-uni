package com.example.pathfinder.services;

import com.example.pathfinder.model.service.UserLoginServiceModel;
import com.example.pathfinder.model.service.UserRegisterServiceModel;
import com.example.pathfinder.model.view.ProfileViewModel;

public interface UserService {
    void register(UserRegisterServiceModel userRegisterServiceModel);
    boolean userExists(UserRegisterServiceModel userRegisterServiceModel);
    ProfileViewModel showProfile(String email);

}
