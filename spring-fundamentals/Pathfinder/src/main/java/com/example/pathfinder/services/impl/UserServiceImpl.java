package com.example.pathfinder.services.impl;

import com.example.pathfinder.model.LevelEnum;
import com.example.pathfinder.model.RoleEnum;
import com.example.pathfinder.model.User;
import com.example.pathfinder.model.service.UserRegisterServiceModel;
import com.example.pathfinder.model.view.ProfileViewModel;
import com.example.pathfinder.repositories.RoleRepository;
import com.example.pathfinder.repositories.UserRepository;
import com.example.pathfinder.services.UserService;
import com.example.pathfinder.web.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;


    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public void register(UserRegisterServiceModel userRegisterServiceModel) {
        User user = new User().setUsername(userRegisterServiceModel.getUsername())
                .setEmail(userRegisterServiceModel.getEmail())
                .setFullName(userRegisterServiceModel.getFullName())
                .setPassword(passwordEncoder.encode(userRegisterServiceModel.getPassword()))
                .setAge(userRegisterServiceModel.getAge())
                .setLevel(LevelEnum.BEGINNER)
                .setRoles(Set.of(roleRepository.findByRole(RoleEnum.USER)));
        userRepository.save(user);
    }

    @Override
    public boolean userExists(UserRegisterServiceModel userRegisterServiceModel) {
        return userRepository.findByUsernameIgnoreCase(userRegisterServiceModel.getUsername()).isPresent();
    }


    @Override
    public ProfileViewModel showProfile(String email) {
        Optional<User> byId = userRepository.findByEmail(email);
        return modelMapper.map(byId.get(), ProfileViewModel.class);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByEmail(username).orElseThrow(() -> new ObjectNotFoundException("User with name " + username + " not found!"));
    }


}
