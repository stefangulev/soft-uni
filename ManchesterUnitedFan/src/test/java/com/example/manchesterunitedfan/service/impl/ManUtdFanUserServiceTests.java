package com.example.manchesterunitedfan.service.impl;

import com.example.manchesterunitedfan.model.entities.UserEntity;
import com.example.manchesterunitedfan.model.entities.UserRoleEntity;
import com.example.manchesterunitedfan.model.enums.UserRoleEnum;
import com.example.manchesterunitedfan.repository.UserRepository;
import com.example.manchesterunitedfan.service.UserService;
import com.example.manchesterunitedfan.service.exceptions.UserNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class ManUtdFanUserServiceTests {

    @Mock
    private UserRepository mockUserRepository;
    private ManUtdFanUserService manUtdFanUserService;
    private UserEntity testUser;
    private UserRoleEntity adminRole, userRole;

    @BeforeEach
    void init() {
        adminRole = new UserRoleEntity().setName(UserRoleEnum.ADMIN);
        userRole = new UserRoleEntity().setName(UserRoleEnum.USER);
        manUtdFanUserService = new ManUtdFanUserService(mockUserRepository);
        testUser = new UserEntity()
                .setUsername("stefan")
                .setPassword("password").setRole(Set.of(adminRole, userRole));
    }

    @Test
    void userNotFound() {
        Assertions.assertThrows(UsernameNotFoundException.class, () ->
                manUtdFanUserService.loadUserByUsername(testUser.getUsername()));
    }

    @Test
    void userFound() {
        Mockito.when(mockUserRepository.findByUsername(testUser.getUsername()))
                .thenReturn(Optional.of(testUser));
        var actual = manUtdFanUserService.loadUserByUsername(testUser.getUsername());
        Assertions.assertEquals(actual.getUsername(), testUser.getUsername());
        Assertions.assertEquals(actual.getPassword(), testUser.getPassword());
        String actualRoles = actual.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(", "));
        String expectedRoles = "ROLE_ADMIN, ROLE_USER";
        Assertions.assertEquals(actualRoles, expectedRoles);
    }
}
