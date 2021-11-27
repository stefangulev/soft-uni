package com.example.pathfinder.web;

import com.example.pathfinder.model.LevelEnum;
import com.example.pathfinder.model.Role;
import com.example.pathfinder.model.RoleEnum;
import com.example.pathfinder.model.User;
import com.example.pathfinder.repositories.RoleRepository;
import com.example.pathfinder.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private final String TEST_EMAIL = "petar@gmail.com";
    private final Integer TEST_AGE = 12;


    @BeforeEach
    public void addRole() {
        roleRepository.save(new Role().setRole(RoleEnum.USER));
    }

    @AfterEach
    private void removeAll() {
        userRepository.deleteAll();
        roleRepository.deleteAll();
    }

    @Test
    void testOpenRegisterForm() throws Exception {
        mockMvc.
                perform(get("/users/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));
    }

    @Test
    void testRegisterUser() throws Exception {
        mockMvc.
                perform(post("/users/register")
                .param("username", "pesho")
                .param("password", "12345")
                .param("confirmPassword", "12345")
                .param("fullName", "Petar Petrov")
                .param("age", String.valueOf(TEST_AGE))
                .param("email", TEST_EMAIL)
                .with(csrf())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection());

        Assertions.assertEquals(userRepository.count(), 1);
        Optional<User> byEmail = userRepository.findByEmail(TEST_EMAIL);
        Assertions.assertTrue(byEmail.isPresent());

        User user = byEmail.get();
        Assertions.assertEquals(user.getAge(), TEST_AGE);

    }


}