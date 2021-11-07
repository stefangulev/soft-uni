package bg.softuni.mobilelele.services;

import bg.softuni.mobilelele.model.entities.*;
import bg.softuni.mobilelele.model.service.UserRegisterServiceModel;
import bg.softuni.mobilelele.repositories.UserRepository;
import bg.softuni.mobilelele.repositories.UserRoleRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final MobileleUserService mobileleUserService;

    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder, MobileleUserService mobileleUserService) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.mobileleUserService = mobileleUserService;
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

        UserDetails principal = mobileleUserService.loadUserByUsername(user.getUsername());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
                user.getPassword(),
                principal.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public boolean usernameFree(String username) {
        return userRepository.findByUsernameIgnoreCase(username).isEmpty();
    }

    @Override
    public UserEntity findUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

}
