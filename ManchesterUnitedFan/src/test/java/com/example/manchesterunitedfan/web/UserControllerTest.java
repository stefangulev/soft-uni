package com.example.manchesterunitedfan.web;

import com.example.manchesterunitedfan.model.entities.PlayerEntity;
import com.example.manchesterunitedfan.model.entities.UserEntity;
import com.example.manchesterunitedfan.model.entities.UserRoleEntity;
import com.example.manchesterunitedfan.model.enums.UserRoleEnum;
import com.example.manchesterunitedfan.repository.UserRepository;
import com.example.manchesterunitedfan.repository.UserRoleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    private final String USERNAME = "stefan";
    private final String PASSWORD = "password";
    private final String CONFIRM_PASSWORD = "password";
    private final String EMAIL = "stefan@abv.bg";
    private final BigDecimal ACCOUNT_BALANCE = BigDecimal.valueOf(10.00);
    private final BigDecimal DEPOSIT_AMOUNT = BigDecimal.valueOf(10.00);
    private final String UPDATED_PASSWORD = "password1";
    private final String UPDATED_CONFIRM_PASSWORD = "password1";
    private final String IMAGE_URL = "New URL that is long enough";

    @AfterEach
    void deleteAll() {
        userRepository.deleteAll();
        userRoleRepository.deleteAll();
    }

    @Test
    void getRegisterPageAnonymous() throws Exception {
        mockMvc.
                perform(get("/users/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));
    }

    @Test
    void postRegister() throws Exception {
        initRoles();
        mockMvc.
                perform(post("/users/register")
                        .param("username", USERNAME)
                        .param("password", PASSWORD)
                        .param("confirmPassword", CONFIRM_PASSWORD)
                        .param("email", EMAIL)
                        .param("accountBalance", String.valueOf(ACCOUNT_BALANCE))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection());


        Assertions.assertEquals(userRepository.count(), 1);
        Optional<UserEntity> user = userRepository.findByUsername(USERNAME);
        Assertions.assertTrue(user.isPresent());
        Assertions.assertEquals(user.get().getUsername(), USERNAME);
        Assertions.assertEquals(user.get().getEmail(), EMAIL);
    }
    @Test
    void postRegisterInvalidInput() throws Exception {
        initRoles();
        mockMvc.
                perform(post("/users/register")
                        .param("username", "u")
                        .param("password", PASSWORD)
                        .param("confirmPassword", CONFIRM_PASSWORD)
                        .param("email", EMAIL)
                        .param("accountBalance", String.valueOf(ACCOUNT_BALANCE))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection());


        Assertions.assertEquals(userRepository.count(), 0);
        Optional<UserEntity> user = userRepository.findByUsername(USERNAME);
        Assertions.assertFalse(user.isPresent());
    }
    @Test
    void postRegisterUsernameAlreadyTaken() throws Exception {
        initRoles();
        initUserEntity();
        mockMvc.
                perform(post("/users/register")
                        .param("username", USERNAME)
                        .param("password", PASSWORD)
                        .param("confirmPassword", CONFIRM_PASSWORD)
                        .param("email", EMAIL)
                        .param("accountBalance", String.valueOf(ACCOUNT_BALANCE))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection()).andExpect(flash().attribute("userExists", true));


        Assertions.assertEquals(userRepository.count(), 1);
    }
    @Test
    void postRegisterDifferentPasswords() throws Exception {
        initRoles();
        mockMvc.
                perform(post("/users/register")
                        .param("username", USERNAME)
                        .param("password", PASSWORD)
                        .param("confirmPassword", "different")
                        .param("email", EMAIL)
                        .param("accountBalance", String.valueOf(ACCOUNT_BALANCE))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection()).andExpect(flash().attribute("differentPasswords", true));


        Assertions.assertEquals(userRepository.count(), 0);
    }

    @WithMockUser(value = "stefan")
    @Test
    void getProfilePage() throws Exception {
        initUserEntity();
        mockMvc.
                perform(get("/users/profile"))
                .andExpect(status().isOk())
                .andExpect(view().name("profile"));
    }

    @WithMockUser(value = "stefan")
    @Test
    void changePassword() throws Exception {
        UserEntity userEntity = initUserEntity();
        mockMvc.perform(patch("/users/profile/change-password")
        .param("password", UPDATED_PASSWORD)
        .param("confrimPassword", UPDATED_CONFIRM_PASSWORD)
        .with(csrf()).contentType(MediaType.APPLICATION_FORM_URLENCODED)).andExpect(status().is3xxRedirection()).andExpect(flash().attribute("passwordChanged", true));
        Optional<UserEntity> user = userRepository.findByUsername(userEntity.getUsername());
        Assertions.assertTrue(passwordEncoder.matches(UPDATED_PASSWORD, user.get().getPassword()));
    }
    @WithMockUser(value = "stefan")
    @Test
    void changePasswordPassNotMatching() throws Exception {
        UserEntity userEntity = initUserEntity();
        mockMvc.perform(patch("/users/profile/change-password")
                .param("password", UPDATED_PASSWORD)
                .param("confrimPassword", "different")
                .with(csrf()).contentType(MediaType.APPLICATION_FORM_URLENCODED)).andExpect(status().is3xxRedirection()).andExpect(flash().attribute("differentPasswords", true));
    }
    @WithMockUser(value = "stefan")
    @Test
    void depositFunds() throws Exception {
        UserEntity userEntity = initUserEntity();
        mockMvc.perform(patch("/users/profile/deposit")
        .param("deposit", String.valueOf(DEPOSIT_AMOUNT)).with(csrf()).contentType(MediaType.APPLICATION_FORM_URLENCODED))
        .andExpect(status().is3xxRedirection()).andExpect(flash().attribute("successfulDeposit",true));

        Optional<UserEntity> user = userRepository.findByUsername(userEntity.getUsername());
        Assertions.assertEquals(user.get().getAccountBalance().longValue(), (ACCOUNT_BALANCE.add(DEPOSIT_AMOUNT).longValue()));
    }
    @WithMockUser(value = "stefan")
    @Test
    void depositFundsInvalidInput() throws Exception {
        UserEntity userEntity = initUserEntity();
        mockMvc.perform(patch("/users/profile/deposit")
                .param("deposit", String.valueOf(BigDecimal.valueOf(-1))).with(csrf()).contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection());

        Optional<UserEntity> user = userRepository.findByUsername(userEntity.getUsername());
        Assertions.assertEquals(user.get().getAccountBalance().longValue(), ACCOUNT_BALANCE.longValue());
    }
    @WithMockUser(value = "stefan")
    @Test
    void updatePicture() throws Exception {
        UserEntity userEntity = initUserEntity();
        mockMvc.perform(patch("/users/profile/update-picture")
                .param("imgUrl", IMAGE_URL).with(csrf()).contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection()).andExpect(flash().attribute("pictureUpdated",true));

        Optional<UserEntity> user = userRepository.findByUsername(userEntity.getUsername());
        Assertions.assertEquals(user.get().getImgUrl(), IMAGE_URL);
    }
    @WithMockUser(value = "stefan")
    @Test
    void updatePictureInvalidInput() throws Exception {
        UserEntity userEntity = initUserEntity();
        mockMvc.perform(patch("/users/profile/update-picture")
                .param("imgUrl", "inv").with(csrf()).contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection());

        Optional<UserEntity> user = userRepository.findByUsername(userEntity.getUsername());
        Assertions.assertNull(user.get().getImgUrl());
    }






    @Test
    void getLoginPageAnonymous() throws Exception {
        mockMvc.
                perform(get("/users/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    void initRoles() {
        UserRoleEntity roleEntity = new UserRoleEntity().setName(UserRoleEnum.USER);
        UserRoleEntity roleEntity1 = new UserRoleEntity().setName(UserRoleEnum.ADMIN);
        userRoleRepository.saveAll(Set.of(roleEntity, roleEntity1));
    }
    UserEntity initUserEntity() {
        UserEntity userEntity = new UserEntity()
                .setUsername("stefan")
                .setPassword("12345")
                .setAccountBalance(ACCOUNT_BALANCE)
                .setEmail("stefan@abv.bg");
        return userRepository.save(userEntity);
    }
}
