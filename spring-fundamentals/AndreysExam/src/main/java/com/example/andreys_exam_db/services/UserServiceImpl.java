package com.example.andreys_exam_db.services;

import com.example.andreys_exam_db.model.User;
import com.example.andreys_exam_db.model.service.UserLoginServiceModel;
import com.example.andreys_exam_db.model.service.UserRegisterServiceModel;
import com.example.andreys_exam_db.repositories.UserRepository;
import com.example.andreys_exam_db.user.CurrentUser;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
    }

    @Override
    public boolean userExistsByUsernameAndEmail(UserRegisterServiceModel userRegisterServiceModel) {
        return userRepository.findUserByUsernameAndEmail(userRegisterServiceModel.getUsername(),
                userRegisterServiceModel.getEmail()) != null;
    }

    @Override
    public boolean userExistsByUsernameAndPassword(UserRegisterServiceModel userRegisterServiceModel) {
        return userRepository.findUserByUsernameAndPassword(userRegisterServiceModel.getUsername(),
                userRegisterServiceModel.getPassword()) != null;
    }

    @Override
    public void register(UserRegisterServiceModel userService) {
        User user = new User().setUsername(userService.getUsername())
                .setEmail(userService.getEmail()).setPassword(userService.getPassword());
        userRepository.save(user);

    }

    @Override
    public void login(UserLoginServiceModel loginServiceModel) {
        User userByUsernameAndPassword = userRepository.findUserByUsernameAndPassword(loginServiceModel.getUsername(), loginServiceModel.getPassword());
        currentUser.setId(userByUsernameAndPassword.getId()).setLoggedIn(true);
    }
}
