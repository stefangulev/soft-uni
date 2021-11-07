package bg.softuni.mobilelele.services;

import bg.softuni.mobilelele.model.entities.UserEntity;
import bg.softuni.mobilelele.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class MobileleUserService implements UserDetailsService {
    private final UserRepository userRepository;

    public MobileleUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // The purpose of this method is to map our user representation (UserEntity)
        // to the user representation in the spring sercurity world (UserDetails).
        // The only thing that spring will provide to us is the user name.
        // The user name will come from the HTML login form.
        return mapToUserDetails(userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User with name " + username + " not found!")));
    }

    public UserDetails mapToUserDetails(UserEntity entity) {

        List<GrantedAuthority> roles = entity.getRoles().stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getRole().name())).collect(Collectors.toList());
        return new MobileleleUser(
                entity.getUsername(),
                entity.getPassword(),
                roles
        );
    }
}
