package com.example.manchesterunitedfan.service.impl;

import com.example.manchesterunitedfan.model.entities.UserEntity;
import com.example.manchesterunitedfan.repository.UserRepository;
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
public class ManUtdFanUserService implements UserDetailsService {
    private final UserRepository userRepository;

    public ManUtdFanUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return mapToUserDetails(userRepository.findByUsername(s)
                .orElseThrow(() -> new UsernameNotFoundException("User with name " + s + " not found!")));
    }

    public UserDetails mapToUserDetails(UserEntity entity) {
        List<GrantedAuthority> roles = entity.getRole().stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getName().name()))
                .collect(Collectors.toList());
        return new User(
                entity.getUsername(),
                entity.getPassword(),
                roles
        );
    }
}
