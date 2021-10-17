package bg.softuni.mobilelele.services;

import bg.softuni.mobilelele.model.entities.*;
import bg.softuni.mobilelele.model.service.UserLoginServiceModel;
import bg.softuni.mobilelele.model.service.UserRegisterServiceModel;
import bg.softuni.mobilelele.repositories.UserRepository;
import bg.softuni.mobilelele.repositories.UserRoleRepository;
import bg.softuni.mobilelele.user.CurrentUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.currentUser = currentUser;
    }

    @Override
    public void registerUser(UserRegisterServiceModel userRegisterServiceModel) {

            UserEntity user = new UserEntity()
                    .setFirstName(userRegisterServiceModel.getFirstName())
                    .setLastName(userRegisterServiceModel.getLastName())
                    .setUsername(userRegisterServiceModel.getUsername() )
                    .setPassword(passwordEncoder.encode(userRegisterServiceModel.getPassword()))
                    .setActive(true);
                    user.getRoles().add(userRoleRepository.findUserRoleEntityByRole(UserRoleEnum.USER));

                    userRepository.save(user);
                    login(user);
    }

    @Override
    public boolean loginUser(UserLoginServiceModel userLoginServiceModel) {
        Optional<UserEntity> byUsername = userRepository.findByUsername(userLoginServiceModel.getUsername());
        if (byUsername.isEmpty()) {
            logout();
            return false;
        } else {
            boolean success = passwordEncoder.matches(userLoginServiceModel.getPassword(), byUsername.get().getPassword());
            if(success) {
                UserEntity loggedIn = byUsername.get();
                login(loggedIn);

            }
            return success;
        }
    }

    public void logout() {
        currentUser.clean();
    }

    @Override
    public boolean usernameFree(String username) {
        return userRepository.findByUsernameIgnoreCase(username).isEmpty();
    }

    @Override
    public UserEntity findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void login(UserEntity inputUser) {
        currentUser.setLoggedIn(true)
                .setUsername(inputUser.getUsername())
                .setFirstName(inputUser.getFirstName())
                .setLastName(inputUser.getLastName())
                .setRoles(inputUser.getRoles())
        .setId(inputUser.getId());
    }
}
