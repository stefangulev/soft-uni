package com.example.coffeeshop.services.impl;

import com.example.coffeeshop.model.entities.User;
import com.example.coffeeshop.model.service.UserLoginServiceModel;
import com.example.coffeeshop.model.service.UserRegisterServiceModel;
import com.example.coffeeshop.model.view.EmployeeViewModel;
import com.example.coffeeshop.repositories.UserRepository;
import com.example.coffeeshop.services.UserService;
import com.example.coffeeshop.user.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final CurrentUser currentUser;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, CurrentUser currentUser, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.modelMapper = modelMapper;
    }


    @Override
    public boolean userWithEmailOrUsernameExists(String username, String email) {
        User userByUsernameOrderByEmail = userRepository.findUserByUsernameOrEmail(username,email);
        return userByUsernameOrderByEmail != null;
    }

    @Override
    public boolean userWithUsernameAndPasswordCombinationExists(String username, String password) {
        User userByUsernameAndPassword = userRepository.findUserByUsernameAndPassword(username, password);
        return userByUsernameAndPassword != null;
    }

    @Override
    public void register(UserRegisterServiceModel serviceModel) {
        User user = new User().setUsername(serviceModel.getUsername())
                .setEmail(serviceModel.getEmail())
                .setFirstName(serviceModel.getFirstName())
                .setLastName(serviceModel.getLastName())
                .setPassword(serviceModel.getPassword());
        userRepository.save(user);
    }

    @Override
    public void login(UserLoginServiceModel serviceModel) {
        User userByUsernameAndPassword = userRepository.findUserByUsernameAndPassword(serviceModel.getUsername(),
                serviceModel.getPassword());
        currentUser.setId(userByUsernameAndPassword.getId()).setUsername(userByUsernameAndPassword.getUsername())
                .setLoggedIn(true);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<EmployeeViewModel> findUsersOrderedByOrderCountDesc() {
        return userRepository.findAllUsersOrderedByCountDesc().stream().map(u -> modelMapper.map(u, EmployeeViewModel.class)).collect(Collectors.toList());
    }
}
