package com.example.pathfinder.services.impl;

import com.example.pathfinder.model.User;
import com.example.pathfinder.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
@Service
public class PathFinderUserService implements UserDetailsService {
   private final UserRepository userRepository;

    public PathFinderUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       return userRepository.findByEmail(email).map(this::mapToUserDetails).orElseThrow(() -> new UsernameNotFoundException("User with email not found!"));
    }

    private UserDetails mapToUserDetails(User user) {
        Set<GrantedAuthority> grantedAuthorities = user.getRoles().stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r.getRole().name())).collect(Collectors.toSet());
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                grantedAuthorities
        );
    }
}
