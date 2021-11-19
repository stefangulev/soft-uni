package com.example.manchesterunitedfan.service.impl;

import com.example.manchesterunitedfan.model.entities.ProductEntity;
import com.example.manchesterunitedfan.model.entities.UserEntity;
import com.example.manchesterunitedfan.model.entities.UserRoleEntity;
import com.example.manchesterunitedfan.model.enums.UserRoleEnum;
import com.example.manchesterunitedfan.model.service.ChangePasswordServiceModel;
import com.example.manchesterunitedfan.model.service.DepositFundsServiceModel;
import com.example.manchesterunitedfan.model.service.UpdateProfileServiceModel;
import com.example.manchesterunitedfan.model.service.UserRegisterServiceModel;
import com.example.manchesterunitedfan.model.view.UserProfileView;
import com.example.manchesterunitedfan.repository.UserRepository;
import com.example.manchesterunitedfan.repository.UserRoleRepository;
import com.example.manchesterunitedfan.service.ProductService;
import com.example.manchesterunitedfan.service.UserService;
import com.example.manchesterunitedfan.service.exceptions.UserNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final ProductService productService;

    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, ManUtdFanUserService manUtdFanUserService, PasswordEncoder passwordEncoder, ModelMapper modelMapper, ProductService productService) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.manUtdFanUserService = manUtdFanUserService;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.productService = productService;
    }

    @Override
    public UserEntity findUserEntityByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User with name " + username + " was not found!"));
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
                .setImgUrl(userRegisterServiceModel.getImgUrl())
                .setAccountBalance(userRegisterServiceModel.getAccountBalance());

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
        return userRepository.findByUsername(username).map(u -> modelMapper.map(u, UserProfileView.class))
                .orElseThrow(() -> new UserNotFoundException("User with name " + username + " was not found!"));
    }

    @Override
    public void changePassword(ChangePasswordServiceModel changePasswordServiceModel, String name) {
        UserEntity user = findUserEntityByUsername(name);
        user.setPassword(passwordEncoder.encode(changePasswordServiceModel.getPassword()));
        userRepository.save(user);
    }

    @Override
    public boolean isAdmin(String username) {
        Optional<UserEntity> byUsername = userRepository.findByUsername(username);
        if(byUsername.isEmpty()) {
            return false;
        }
      return byUsername.get().getRole().stream().map(UserRoleEntity::getName).anyMatch(r -> r == UserRoleEnum.ADMIN);
    }

    @Override
    public void buyProduct(UserEntity buyer, ProductEntity product) {
        buyer.getOwnedItems().add(product);
        buyer.setAccountBalance(buyer.getAccountBalance().subtract(product.getPrice()));
        userRepository.save(buyer);
        productService.soldProduct(product);

    }

    @Override
    public void depositFunds(DepositFundsServiceModel serviceModel, String name) {
        UserEntity user = findUserEntityByUsername(name);
        user.setAccountBalance(user.getAccountBalance().add(serviceModel.getDeposit()));
        userRepository.save(user);
    }

    @Override
    public void updateProfilePicture(UpdateProfileServiceModel serviceModel, String name) {
        UserEntity user = findUserEntityByUsername(name);
        user.setImgUrl(serviceModel.getImgUrl());
        userRepository.save(user);
    }
}
