package com.example.coffeeshop.services;

import com.example.coffeeshop.model.binding.UserLoginBindingModel;
import com.example.coffeeshop.model.entities.User;
import com.example.coffeeshop.model.service.UserLoginServiceModel;
import com.example.coffeeshop.model.service.UserRegisterServiceModel;
import com.example.coffeeshop.model.view.EmployeeViewModel;

import java.util.List;

public interface UserService {

    boolean userWithEmailOrUsernameExists(String username, String email);
    boolean userWithUsernameAndPasswordCombinationExists(String username, String password);
    void register(UserRegisterServiceModel serviceModel);
    void login(UserLoginServiceModel serviceModel);
    User findUserById(Long id);
    List<EmployeeViewModel> findUsersOrderedByOrderCountDesc();
}
