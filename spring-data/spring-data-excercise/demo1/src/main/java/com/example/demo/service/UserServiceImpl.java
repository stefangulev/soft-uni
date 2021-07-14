package com.example.demo.service;

import com.example.demo.models.dtos.LoginUserDto;
import com.example.demo.models.dtos.RegisterUserDto;
import com.example.demo.models.entities.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import java.util.Set;

@Component
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private User loggedInUser;

    public UserServiceImpl(UserRepository userRepository,ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void registerUser(RegisterUserDto registerUserDto) {
        if (!registerUserDto.getPassword().equals(registerUserDto.getConfirmPassword())) {
            System.out.println("Passwords do not match!");
            return;
        }
        Set<ConstraintViolation<RegisterUserDto>> violation = validationUtil.violation(registerUserDto);
        if (!violation.isEmpty()) {
            violation.stream().map(ConstraintViolation::getMessage).forEach(System.out::println);
            return;
        }

        User user = modelMapper.map(registerUserDto, User.class);

        userRepository.save(user);
        System.out.println("Ivan was successfully registered!");
    }

    @Override
    public void loginUser(LoginUserDto loginUserDto) {
        Set<ConstraintViolation<LoginUserDto>> violation = validationUtil.violation(loginUserDto);
        if (!violation.isEmpty()) {
            violation.stream().map(ConstraintViolation::getMessage).forEach(System.out::println);
            return;
        }
        User user = userRepository.findByEmailAndPassword(loginUserDto.getEmail(), loginUserDto.getPassword()).orElse(null);
        if (user == null) {
            System.out.println("Incorrect username/password");
            return;
        }

        this.loggedInUser = user;
        System.out.printf("Successfully logged in user %s%n", this.loggedInUser.getFullName());
    }

    @Override
    public void logout() {
        if (this.loggedInUser == null) {
            System.out.println("Cannot log out. No user was logged in.");
        } else {
            String name = this.loggedInUser.getFullName();
            this.loggedInUser = null;
            System.out.println(name + " was logged out.");
        }
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }
}
