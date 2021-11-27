package com.example.manchesterunitedfan.web;

import com.example.manchesterunitedfan.model.entities.NewsArticleEntity;
import com.example.manchesterunitedfan.model.entities.StadiumVisitEntity;
import com.example.manchesterunitedfan.model.entities.UserEntity;
import com.example.manchesterunitedfan.model.entities.UserRoleEntity;
import com.example.manchesterunitedfan.repository.StadiumVisitRepository;
import com.example.manchesterunitedfan.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class StadiumControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StadiumVisitRepository stadiumVisitRepository;
    private final String ADDITIONAL_INFORMATION = "We might be there early";
    private final int VISITORS_NUMBER = 2;
    private final LocalDateTime VISIT_DATE = LocalDateTime.of(2023, 3, 13, 8, 24);

    @AfterEach
    void deleteAll() {
        stadiumVisitRepository.deleteAll();
        userRepository.deleteAll();
    }

    @WithMockUser(value = "stefan", roles = {"ADMIN", "USER"})
    @Test
    void getNewsPageAuthorized() throws Exception {
        mockMvc.
                perform(get("/stadium"))
                .andExpect(status().isOk())
                .andExpect(view().name("stadium"))
                .andExpect(model().attributeExists("visits"));
    }
    @Test
    void getStadiumPageAnonymous() throws Exception {
        mockMvc
                .perform(get("/stadium"))
                .andExpect(status().is3xxRedirection());
    }

    @WithMockUser(value = "stefan", roles = {"ADMIN", "USER"})
    @Test
    void postStadiumVisit() throws Exception {
        userRepository.save(initUserEntity());
        mockMvc.perform(post("/stadium")
        .param("date", VISIT_DATE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")))
                .param("visitors", String.valueOf(2))
                .param("additionalInformation", ADDITIONAL_INFORMATION)
                .with(csrf())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
        .andExpect(status().is3xxRedirection());

        Assertions.assertEquals(stadiumVisitRepository.count(), 1);

        Optional<StadiumVisitEntity> stadiumVisitEntitiesByAdditionalInformation = stadiumVisitRepository.findStadiumVisitEntitiesByAdditionalInformation(ADDITIONAL_INFORMATION);

        Assertions.assertTrue(stadiumVisitEntitiesByAdditionalInformation.isPresent());
        Assertions.assertEquals(stadiumVisitEntitiesByAdditionalInformation.get().getAdditionalInformation(), ADDITIONAL_INFORMATION);
        Assertions.assertEquals(stadiumVisitEntitiesByAdditionalInformation.get().getVisitors(), VISITORS_NUMBER);
        Assertions.assertEquals(stadiumVisitEntitiesByAdditionalInformation.get().getDate(), VISIT_DATE);
    }
    @WithMockUser(value = "stefan")
    @Test
    void postStadiumVisitInvalidInput() throws Exception {
        userRepository.save(initUserEntity());
        mockMvc.perform(post("/stadium")
                .param("date", LocalDateTime.of(2023, 3, 13, 8, 24).format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")))
                .param("visitors", String.valueOf(-13))
                .param("additionalInformation", ADDITIONAL_INFORMATION)
                .with(csrf())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection());

        Assertions.assertEquals(stadiumVisitRepository.count(), 0);

        Optional<StadiumVisitEntity> stadiumVisitEntitiesByAdditionalInformation = stadiumVisitRepository.findStadiumVisitEntitiesByAdditionalInformation(ADDITIONAL_INFORMATION);

        Assertions.assertFalse(stadiumVisitEntitiesByAdditionalInformation.isPresent());
    }
    @Test
    void postStadiumVisitAnonymous() throws Exception {
        userRepository.save(initUserEntity());
        mockMvc.perform(post("/stadium")
                .param("date", LocalDateTime.of(2023, 3, 13, 8, 24).format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")))
                .param("visitors", String.valueOf(VISITORS_NUMBER))
                .param("additionalInformation", ADDITIONAL_INFORMATION)
                .with(csrf())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection());

        Assertions.assertEquals(stadiumVisitRepository.count(), 0);

        Optional<StadiumVisitEntity> stadiumVisitEntitiesByAdditionalInformation = stadiumVisitRepository.findStadiumVisitEntitiesByAdditionalInformation(ADDITIONAL_INFORMATION);

        Assertions.assertFalse(stadiumVisitEntitiesByAdditionalInformation.isPresent());
    }


    @WithMockUser(value = "stefan")
    @Test
    void deleteArticleAuthorized() throws Exception {
        UserEntity userEntity = initUserEntity();
        StadiumVisitEntity stadiumVisitEntity = initStadiumVisit(userEntity);
        mockMvc.perform(delete("/stadium/delete/" + stadiumVisitEntity.getId()).with(csrf()))
                .andExpect(status().is3xxRedirection());
        Assertions.assertEquals(stadiumVisitRepository.count(), 0);
        Optional<StadiumVisitEntity> visit = stadiumVisitRepository.findById(stadiumVisitEntity.getId());
        Assertions.assertFalse(visit.isPresent());
    }
    @WithMockUser(value = "petkan")
    @Test
    void deleteArticleUnauthorized() throws Exception {
        UserEntity userEntity = initUserEntity();
        StadiumVisitEntity stadiumVisitEntity = initStadiumVisit(userEntity);
        mockMvc.perform(delete("/news/delete/" + stadiumVisitEntity.getId()).with(csrf()))
                .andExpect(status().isForbidden());
    }

    UserEntity initUserEntity() {
        UserEntity userEntity = new UserEntity()
                .setUsername("stefan")
                .setPassword("12345")
                .setAccountBalance(BigDecimal.ONE)
                .setEmail("stefan@abv.bg");
        return userRepository.save(userEntity);
    }
    StadiumVisitEntity initStadiumVisit(UserEntity userEntity) {
        StadiumVisitEntity stadiumVisitEntity = new StadiumVisitEntity()
                .setDate(VISIT_DATE)
                .setVisitors(VISITORS_NUMBER)
                .setAdditionalInformation(ADDITIONAL_INFORMATION)
                .setUser(userEntity);
        return  stadiumVisitRepository.save(stadiumVisitEntity);

    }

}
