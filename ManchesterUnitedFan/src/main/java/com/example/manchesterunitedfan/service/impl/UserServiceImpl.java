package com.example.manchesterunitedfan.service.impl;

import com.example.manchesterunitedfan.model.entities.UserEntity;
import com.example.manchesterunitedfan.model.enums.UserRoleEnum;
import com.example.manchesterunitedfan.model.service.UpdateProfileServiceModel;
import com.example.manchesterunitedfan.model.service.UserRegisterServiceModel;
import com.example.manchesterunitedfan.model.view.UserProfileView;
import com.example.manchesterunitedfan.repository.UserRepository;
import com.example.manchesterunitedfan.repository.UserRoleRepository;
import com.example.manchesterunitedfan.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final ManUtdFanUserService manUtdFanUserService;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, ManUtdFanUserService manUtdFanUserService, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.manUtdFanUserService = manUtdFanUserService;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserEntity findUserEntityByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public UserEntity findUserEntityByUsernameOrEmail(String username, String email) {
        return userRepository.findByUsernameOrEmail(username, email).orElse(null);
    }

    @Override
    public void register(UserRegisterServiceModel userRegisterServiceModel) {
        UserEntity userEntity = new UserEntity().setUsername(userRegisterServiceModel.getUsername())
                .setPassword(passwordEncoder.encode(userRegisterServiceModel.getPassword()))
                .setEmail(userRegisterServiceModel.getEmail())
                .setImgUrl(userRegisterServiceModel.getImgUrl());

        if(userRepository.count() == 0) {
            userEntity.getRole().add(userRoleRepository.findByName(UserRoleEnum.ADMIN));
        } else {
            userEntity.getRole().add(userRoleRepository.findByName(UserRoleEnum.USER));
        }
        userRepository.save(userEntity);
        UserDetails principal = manUtdFanUserService.loadUserByUsername(userEntity.getUsername());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
                principal.getPassword(),
                principal.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public UserProfileView findProfileViewByUsername(String username) {
        return userRepository.findByUsername(username).map(u -> modelMapper.map(u, UserProfileView.class)).orElse(null);
    }

    @Override
    public void updateProfile(UpdateProfileServiceModel serviceModel, String name) {
      UserEntity user =
                userRepository.findByUsername(name).orElse(null);
      user.setImgUrl(serviceModel.getImgUrl()).setPassword(passwordEncoder.encode(serviceModel.getPassword()));
      userRepository.save(user);
    }
}
