package com.example.pathfinder.service.impl;

import com.example.pathfinder.model.Role;
import com.example.pathfinder.model.RoleEnum;
import com.example.pathfinder.model.User;
import com.example.pathfinder.repositories.UserRepository;
import com.example.pathfinder.services.impl.PathFinderUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
class PathFinderUserServiceTest {

    private User testUser;
    private Role adminRole, userRole;
    private PathFinderUserService serviceToTest;

    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void init() {
        serviceToTest = new PathFinderUserService(mockUserRepository);
        adminRole = new Role().setRole(RoleEnum.ADMIN);
        userRole = new Role().setRole(RoleEnum.USER);
        testUser = new User().setUsername("stefan")
                .setFullName("Stefan Gulev")
                .setEmail("stefan@abv.bg")
                .setPassword("password").setAge(24)
                .setRoles(Set.of(adminRole, userRole));
    }

    @Test
    void testUserNotFound() {
        Assertions.assertThrows(UsernameNotFoundException.class, () ->
                serviceToTest.loadUserByUsername("invalid@usermail.com"));
    }

    @Test
    void testUserFound() {
        Mockito.when(mockUserRepository.findByEmail(testUser.getEmail())).thenReturn(Optional.of(testUser));

        var actual = serviceToTest.loadUserByUsername(testUser.getEmail());

        Assertions.assertEquals(actual.getUsername(), testUser.getEmail());
        String actualRoles = actual.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(", "));
        String expectedRoles = "ROLE_ADMIN, ROLE_USER";
        Assertions.assertEquals(actualRoles, expectedRoles);
    }

}
