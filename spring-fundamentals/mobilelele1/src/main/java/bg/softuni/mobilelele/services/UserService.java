package bg.softuni.mobilelele.services;

import bg.softuni.mobilelele.model.entities.UserEntity;
import bg.softuni.mobilelele.model.service.UserLoginServiceModel;
import bg.softuni.mobilelele.model.service.UserRegisterServiceModel;

public interface UserService {
    void registerUser(UserRegisterServiceModel userRegisterServiceModel);
    boolean loginUser(UserLoginServiceModel userLoginServiceModel);
    void logout();
    boolean usernameFree(String username);
     UserEntity findUserById(Long id);
}
