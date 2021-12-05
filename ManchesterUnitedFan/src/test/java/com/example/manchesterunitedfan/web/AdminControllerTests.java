package com.example.manchesterunitedfan.web;

import com.example.manchesterunitedfan.model.entities.UserEntity;
import com.example.manchesterunitedfan.model.entities.UserRoleEntity;
import com.example.manchesterunitedfan.model.enums.UserRoleEnum;
import com.example.manchesterunitedfan.repository.UserRepository;
import com.example.manchesterunitedfan.repository.UserRoleRepository;
import com.example.manchesterunitedfan.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AdminControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @BeforeEach
    void deleteAll() {
        userRepository.deleteAll();
        userRoleRepository.deleteAll();
    }
    @WithMockUser(value = "stefan", roles = {"ADMIN", "USER"})
    @Test
    void getUsageStatsAuthorized() throws Exception {
        mockMvc.perform(get("/admin/usage-stats"))
                .andExpect(status().isOk())
                .andExpect(view().name("usage-stats"));
    }
    @WithMockUser(value = "stefan", roles = {"USER"})
    @Test
    void getUsageStatsUnauthorized() throws Exception {
        mockMvc.perform(get("/admin/usage-stats"))
                .andExpect(status().isForbidden());
    }
    @Test
    void getUsageStatsAnonymous() throws Exception {
        mockMvc.
                perform(get("/admin/usage-stats"))
                .andExpect(status().is3xxRedirection());
    }

    @WithMockUser(value = "stefan", roles = {"ADMIN", "USER"})
    @Test
    void getSecurityStatsAuthorized() throws Exception {
        mockMvc.perform(get("/admin/security-stats"))
                .andExpect(status().isOk())
                .andExpect(view().name("security-stats"));
    }
    @WithMockUser(value = "stefan", roles = {"USER"})
    @Test
    void getSecurityStatsUnauthorized() throws Exception {
        mockMvc.perform(get("/admin/security-stats"))
                .andExpect(status().isForbidden());
    }
    @Test
    void getSecurityStatsAnonymous() throws Exception {
        mockMvc.
                perform(get("/admin/security-stats"))
                .andExpect(status().is3xxRedirection());
    }
    @WithMockUser(value = "stefan", roles = {"ADMIN", "USER"})
    @Test
    void getRolesAuthorized() throws Exception {
        mockMvc.perform(get("/admin/roles"))
                .andExpect(status().isOk())
                .andExpect(view().name("roles"));
    }
    @WithMockUser(value = "stefan", roles = {"USER"})
    @Test
    void getRolesUnauthorized() throws Exception {
        mockMvc.perform(get("/admin/roles"))
                .andExpect(status().isForbidden());
    }
    @Test
    void getRolesAnonymous() throws Exception {
        mockMvc.
                perform(get("/admin/roles"))
                .andExpect(status().is3xxRedirection());
    }

    @WithMockUser(value = "stefan", roles = {"ADMIN", "USER"})
    @Test
    void patchRolesUserToAdminSelf() throws Exception {
        UserEntity userEntity = initUserEntity(initUserRole());
        initAdminRole();
        mockMvc.perform(patch("/admin/roles/" + userEntity.getId())
        .param("roleSelect", "ADMIN").with(csrf()))
                .andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/users/login"));

        userEntity = userRepository.findById(userEntity.getId()).get();

        Assertions.assertEquals(userEntity.getRole().size(), 1);
        Assertions.assertTrue(userEntity.getRole().stream().map(UserRoleEntity::getName).anyMatch(r -> r == UserRoleEnum.ADMIN));
    }
    @WithMockUser(value = "petkan", roles = {"ADMIN", "USER"})
    @Test
    void patchRolesUserToAdminUser() throws Exception {
        UserEntity userEntity = initUserEntity(initUserRole());
        initAdminRole();
        mockMvc.perform(patch("/admin/roles/" + userEntity.getId())
                .param("roleSelect", "ADMIN & USER").with(csrf()))
                .andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/admin/roles"));

        userEntity = userRepository.findById(userEntity.getId()).get();

        Assertions.assertEquals(userEntity.getRole().size(), 2);
        Assertions.assertTrue(userEntity.getRole().stream().map(UserRoleEntity::getName).anyMatch(r -> r == UserRoleEnum.ADMIN));
        Assertions.assertTrue(userEntity.getRole().stream().map(UserRoleEntity::getName).anyMatch(r -> r == UserRoleEnum.USER));
    }
    @WithMockUser(value = "petkan", roles = {"ADMIN", "USER"})
    @Test
    void patchRolesAdminToUser() throws Exception {
        UserEntity userEntity = initUserEntity(initAdminRole());
        initUserRole();
        mockMvc.perform(patch("/admin/roles/" + userEntity.getId())
                .param("roleSelect", "USER").with(csrf()))
                .andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/admin/roles"));

        userEntity = userRepository.findById(userEntity.getId()).get();

        Assertions.assertEquals(userEntity.getRole().size(), 1);
        Assertions.assertTrue(userEntity.getRole().stream().map(UserRoleEntity::getName).anyMatch(r -> r == UserRoleEnum.USER));
    }
    @WithMockUser(value = "petkan", roles = {"ADMIN", "USER"})
    @Test
    void patchRolesInvalidUser() throws Exception {
        UserEntity userEntity = initUserEntity(initAdminRole());
        initUserRole();
        mockMvc.perform(patch("/admin/roles/" + 150)
                .param("roleSelect", "USER").with(csrf()))
                .andExpect(status().isNotFound());

    }
    @WithMockUser(value = "petkan", roles = {"ADMIN", "USER"})
    @Test
    void patchRolesInvalidChoice() throws Exception {
        UserEntity userEntity = initUserEntity(initAdminRole());
        initUserRole();
        mockMvc.perform(patch("/admin/roles/" + userEntity.getId())
                .param("roleSelect", "INVALID").with(csrf()))
                .andExpect(status().isNotFound());

        userEntity = userRepository.findById(userEntity.getId()).get();

        Assertions.assertEquals(userEntity.getRole().size(), 1);
        Assertions.assertTrue(userEntity.getRole().stream().map(UserRoleEntity::getName).anyMatch(r -> r == UserRoleEnum.ADMIN));

    }

    UserRoleEntity initAdminRole() {
        UserRoleEntity admin = new UserRoleEntity().setName(UserRoleEnum.ADMIN);
        return userRoleRepository.save(admin);
    }
    UserRoleEntity initUserRole() {
        UserRoleEntity user = new UserRoleEntity().setName(UserRoleEnum.USER);
        return userRoleRepository.save(user);
    }
    UserEntity initUserEntity(UserRoleEntity userRoleEntity) {
        UserEntity userEntity = new UserEntity()
                .setUsername("stefan")
                .setPassword("12345")
                .setAccountBalance(BigDecimal.ONE)
                .setEmail("stefan@abv.bg");
        userEntity.getRole().add(userRoleEntity);
        return userRepository.save(userEntity);
    }
}
