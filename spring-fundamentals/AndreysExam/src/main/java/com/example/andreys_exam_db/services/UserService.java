package com.example.andreys_exam_db.services;

import com.example.andreys_exam_db.model.service.UserLoginServiceModel;
import com.example.andreys_exam_db.model.service.UserRegisterServiceModel;

public interface UserService {
    boolean userExistsByUsernameAndEmail(UserRegisterServiceModel userRegisterServiceModel);
    boolean userExistsByUsernameAndPassword(UserRegisterServiceModel userRegisterServiceModel);

    void register(UserRegisterServiceModel userService);

    void login(UserLoginServiceModel loginServiceModel);
}
