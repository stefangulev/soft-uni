package com.example.manchesterunitedfan.service;

import com.example.manchesterunitedfan.model.entities.UserEntity;
import com.example.manchesterunitedfan.model.service.UpdateProfileServiceModel;
import com.example.manchesterunitedfan.model.service.UserRegisterServiceModel;
import com.example.manchesterunitedfan.model.view.UserProfileView;

public interface UserService {
    UserEntity findUserEntityByUsername(String username);
    UserEntity findUserEntityByUsernameOrEmail(String username, String email);
    void register(UserRegisterServiceModel userRegisterServiceModel);
    UserProfileView findProfileViewByUsername(String username);

    void updateProfile(UpdateProfileServiceModel serviceModel, String name);
}
