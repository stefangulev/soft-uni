package com.example.manchesterunitedfan.service;

import com.example.manchesterunitedfan.model.entities.ProductEntity;
import com.example.manchesterunitedfan.model.entities.UserEntity;
import com.example.manchesterunitedfan.model.service.ChangePasswordServiceModel;
import com.example.manchesterunitedfan.model.service.DepositFundsServiceModel;
import com.example.manchesterunitedfan.model.service.UpdateProfileServiceModel;
import com.example.manchesterunitedfan.model.service.UserRegisterServiceModel;
import com.example.manchesterunitedfan.model.view.UserAdminPageView;
import com.example.manchesterunitedfan.model.view.UserProfileView;

import java.util.List;

public interface UserService {
    UserEntity findUserEntityByUsername(String username);
    UserEntity findUserEntityById(Long id);
    List<UserEntity> findUserEntityByUsernameOrEmail(String username, String email);
    void register(UserRegisterServiceModel userRegisterServiceModel);
    UserProfileView findProfileViewByUsername(String username);
    void changePassword(ChangePasswordServiceModel changePasswordServiceModel, String name);
    boolean isAdmin(String username);

    void buyProduct(UserEntity buyer, ProductEntity product);

    void depositFunds(DepositFundsServiceModel serviceModel, String name);

    void updateProfilePicture(UpdateProfileServiceModel serviceModel, String name);
    List<UserAdminPageView> getAllUsersWithRoles();
    void saveEntityFromOtherService(UserEntity userEntity);
}
