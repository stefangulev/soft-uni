package com.example.pathfinder.services.impl;

import com.example.pathfinder.model.LevelEnum;
import com.example.pathfinder.model.RoleEnum;
import com.example.pathfinder.model.User;
import com.example.pathfinder.model.service.UserLoginServiceModel;
import com.example.pathfinder.model.service.UserRegisterServiceModel;
import com.example.pathfinder.model.view.ProfileViewModel;
import com.example.pathfinder.repositories.RoleRepository;
import com.example.pathfinder.repositories.UserRepository;
import com.example.pathfinder.services.UserService;
import com.example.pathfinder.user.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;
    private final CurrentUser currentUser;


    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, RoleRepository roleRepository, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
        this.currentUser = currentUser;
    }

    @Override
    public void register(UserRegisterServiceModel userRegisterServiceModel) {
        User user = modelMapper.map(userRegisterServiceModel, User.class)
                .setLevel(LevelEnum.BEGINNER)
                .setRole(Set.of(roleRepository.findByRole(RoleEnum.USER)));
        userRepository.save(user);
    }

    @Override
    public boolean userExists(UserRegisterServiceModel userRegisterServiceModel) {
        return userRepository.findByUsernameIgnoreCase(userRegisterServiceModel.getUsername()).isPresent();
    }

    @Override
    public boolean loginPasswordCombinationValid(UserLoginServiceModel userLoginServiceModel) {
        return userRepository.findByUsernameAndPassword(userLoginServiceModel.getUsername(), userLoginServiceModel.getPassword()).isPresent();
    }

    @Override
    public void login(UserLoginServiceModel userLoginServiceModel) {
        User byUsernameAndPassword = userRepository.findByUsernameAndPassword(userLoginServiceModel.getUsername(), userLoginServiceModel.getPassword()).orElse(null);
        currentUser.setLoggedIn(true).setUsername(byUsernameAndPassword.getUsername()).setPassword(byUsernameAndPassword.getPassword())
                .setFullName(byUsernameAndPassword.getFullName())
                .setAge(byUsernameAndPassword.getAge())
                .setEmail(byUsernameAndPassword.getEmail())
                .setLevel(byUsernameAndPassword.getLevel())
                .setRole(byUsernameAndPassword.getRole()).setId(byUsernameAndPassword.getId());

    }

    @Override
    public void logout() {
        this.currentUser.clear();
    }

    @Override
    public ProfileViewModel showProfile(Long id) {
        Optional<User> byId = userRepository.findById(id);
        return modelMapper.map(byId.get(), ProfileViewModel.class);
    }


}
