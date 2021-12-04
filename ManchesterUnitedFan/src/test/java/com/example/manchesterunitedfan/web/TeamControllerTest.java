package com.example.manchesterunitedfan.web;

import com.example.manchesterunitedfan.model.entities.NewsArticleEntity;
import com.example.manchesterunitedfan.model.entities.PlayerEntity;
import com.example.manchesterunitedfan.model.entities.ProductEntity;
import com.example.manchesterunitedfan.model.entities.UserRoleEntity;
import com.example.manchesterunitedfan.model.enums.NationalityEnum;
import com.example.manchesterunitedfan.model.enums.PositionEnum;
import com.example.manchesterunitedfan.repository.PlayerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import javax.print.attribute.standard.MediaSize;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TeamControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PlayerRepository playerRepository;

    private final String FIRST_NAME = "Test";
    private final String LAST_NAME = "Player";
    private final Integer SQUAD_NUMBER = 7;
    private final PositionEnum POSITION = PositionEnum.ATTACKER;
    private final Integer AGE = 21;
    private final NationalityEnum NATIONALITY = NationalityEnum.ENGLAND;
    private final String UPDATED_FIRST_NAME = "Updated Test";
    private final String UPDATED_LAST_NAME = "Updated Player";
    private final Integer UPDATED_SQUAD_NUMBER = 10;
    private final PositionEnum UPDATED_POSITION = PositionEnum.MIDFIELDER;
    private final Integer UPDATED_AGE = 22;
    private final NationalityEnum UPDATED_NATIONALITY = NationalityEnum.BRAZIL;

    @BeforeEach
    void deleteAll() {
        playerRepository.deleteAll();
    }

    @WithMockUser(value = "stefan", roles = {"ADMIN", "USER"})
    @Test
    void getSquadPageAuthorized() throws Exception {
        mockMvc.
                perform(get("/team/squad"))
                .andExpect(status().isOk())
                .andExpect(view().name("squad"))
                .andExpect(model().attributeExists("players"));
    }

    @Test
    void getSquadPageAnonymous() throws Exception {
        mockMvc.
                perform(get("/team/squad"))
                .andExpect(status().is3xxRedirection());
    }
    @WithMockUser(value = "stefan", roles = {"ADMIN", "USER"})
    @Test
    void getPlayerDetailsPageAuthorized() throws Exception {
        PlayerEntity playerEntity = initPlayer();
        mockMvc.perform(get("/team/squad/details/" + playerEntity.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("player-details"))
                .andExpect(model().attributeExists("player"));
    }
    @Test
    void getPlayerDetailsPageUnauthorized() throws Exception {
        PlayerEntity playerEntity = initPlayer();
        mockMvc.perform(get("/team/squad/details/" + playerEntity.getId()))
                .andExpect(status().is3xxRedirection());

    }
    @WithMockUser(value = "stefan", roles = {"ADMIN", "USER"})
    @Test
    void getPlayerDetailsNonExistent() throws Exception {
        mockMvc.perform(get("/team/squad/details/" +2))
                .andExpect(status().isNotFound());

    }

    @WithMockUser(value = "stefan", roles = {"ADMIN", "USER"})
    @Test
    void getAddPlayerPageAuthorized() throws Exception {
        mockMvc.
                perform(get("/team/squad/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-player"));
    }
    @WithMockUser(value = "stefan", roles = {"USER"})
    @Test
    void getAddPlayerUnauthorized() throws Exception {
        mockMvc.
                perform(get("/team/squad/add"))
                .andExpect(status().isForbidden());
    }
    @WithMockUser(value = "stefan", roles = {"ADMIN", "USER"})
    @Test
    void postAddPlayerAuthorized() throws Exception {
        mockMvc.
                perform(post("/team/squad/add")
                        .param("firstName", FIRST_NAME)
                        .param("lastName", LAST_NAME)
                        .param("squadNumber", String.valueOf(SQUAD_NUMBER))
                        .param("position", String.valueOf(POSITION))
                        .param("age", String.valueOf(AGE))
                        .param("nationality", String.valueOf(NATIONALITY))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection());


        Assertions.assertEquals(playerRepository.count(), 1);
        Optional<PlayerEntity> playerEntityBySquadNumber =
                playerRepository.findPlayerEntityBySquadNumber(SQUAD_NUMBER);
        Assertions.assertTrue(playerEntityBySquadNumber.isPresent());
        Assertions.assertEquals(playerEntityBySquadNumber.get().getFirstName(), FIRST_NAME);
        Assertions.assertEquals(playerEntityBySquadNumber.get().getLastName(), LAST_NAME);
        Assertions.assertEquals(playerEntityBySquadNumber.get().getSquadNumber(), SQUAD_NUMBER);
        Assertions.assertEquals(playerEntityBySquadNumber.get().getAge(), AGE);
        Assertions.assertEquals(playerEntityBySquadNumber.get().getPosition(), POSITION);
        Assertions.assertEquals(playerEntityBySquadNumber.get().getNationality(), NATIONALITY);

    }
    @WithMockUser(value = "stefan", roles = {"ADMIN", "USER"})
    @Test
    void postAddPlayerInvalidInput() throws Exception {
        mockMvc.
                perform(post("/team/squad/add")
                        .param("firstName", FIRST_NAME)
                        .param("lastName", LAST_NAME)
                        .param("squadNumber", String.valueOf(SQUAD_NUMBER))
                        .param("position", String.valueOf(POSITION))
                        .param("age", String.valueOf(-1))
                        .param("nationality", String.valueOf(NATIONALITY))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection());


        Assertions.assertEquals(playerRepository.count(), 0);

    }
    @WithMockUser(value = "stefan")
    @Test
    void postAddPlayerUnauthorized() throws Exception {
        mockMvc.
                perform(post("/team/squad/add")
                        .param("firstName", FIRST_NAME)
                        .param("lastName", LAST_NAME)
                        .param("squadNumber", String.valueOf(SQUAD_NUMBER))
                        .param("position", String.valueOf(POSITION))
                        .param("age", String.valueOf(AGE))
                        .param("nationality", String.valueOf(NATIONALITY))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isForbidden());


    }

    @WithMockUser(value = "stefan", roles = {"ADMIN", "USER"})
    @Test
    void postAddPlayerSquadNumberTaken() throws Exception {
        PlayerEntity playerEntity = initPlayer();
        mockMvc.
                perform(post("/team/squad/add")
                        .param("firstName", FIRST_NAME)
                        .param("lastName", LAST_NAME)
                        .param("squadNumber", String.valueOf(SQUAD_NUMBER))
                        .param("position", String.valueOf(POSITION))
                        .param("age", String.valueOf(AGE))
                        .param("nationality", String.valueOf(NATIONALITY))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection()).andExpect(flash().attribute("squadNumberTaken", true));


        Assertions.assertEquals(playerRepository.count(), 1);
    }

    @WithMockUser(value = "stefan", roles = {"ADMIN", "USER"})
    @Test
    void getEditPlayerPageAuthorized() throws Exception {
        PlayerEntity playerEntity = initPlayer();
        mockMvc.
                perform(get("/team/squad/edit/" + playerEntity.getId() ))
                .andExpect(status().isOk())
                .andExpect(view().name("edit-player"));
    }
    @WithMockUser(value = "stefan", roles = {"USER"})
    @Test
    void getEditPlayerUnauthorized() throws Exception {
        PlayerEntity playerEntity = initPlayer();
        mockMvc.
                perform(get("/team/squad/edit/" + playerEntity.getId() ))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(value = "stefan", roles = {"ADMIN", "USER"})
    @Test
    void getEditDetailsNonExistent() throws Exception {
        mockMvc.perform(get("/team/squad/edit/" + 2))
                .andExpect(status().isNotFound());

    }

    @WithMockUser(value = "stefan", roles = {"ADMIN", "USER"})
    @Test
    void postEditPlayerAuthorized() throws Exception {

        PlayerEntity playerEntity = initPlayer();
        mockMvc.
                perform(post("/team/squad/edit/" + playerEntity.getId())
                        .param("firstName", UPDATED_FIRST_NAME)
                        .param("lastName", UPDATED_LAST_NAME)
                        .param("squadNumber", String.valueOf(UPDATED_SQUAD_NUMBER))
                        .param("position", String.valueOf(UPDATED_POSITION))
                        .param("age", String.valueOf(UPDATED_AGE))
                        .param("nationality", String.valueOf(UPDATED_NATIONALITY))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection());


        Assertions.assertEquals(playerRepository.count(), 1);
        Optional<PlayerEntity> playerEntityBySquadNumber =
                playerRepository.findById(playerEntity.getId());
        Assertions.assertTrue(playerEntityBySquadNumber.isPresent());
        Assertions.assertEquals(playerEntityBySquadNumber.get().getFirstName(), UPDATED_FIRST_NAME);
        Assertions.assertEquals(playerEntityBySquadNumber.get().getLastName(), UPDATED_LAST_NAME);
        Assertions.assertEquals(playerEntityBySquadNumber.get().getSquadNumber(), UPDATED_SQUAD_NUMBER);
        Assertions.assertEquals(playerEntityBySquadNumber.get().getAge(), UPDATED_AGE);
        Assertions.assertEquals(playerEntityBySquadNumber.get().getPosition(), UPDATED_POSITION);
        Assertions.assertEquals(playerEntityBySquadNumber.get().getNationality(), UPDATED_NATIONALITY);

    }
    @WithMockUser(value = "stefan")
    @Test
    void postEditPlayerUnauthorized() throws Exception {

        PlayerEntity playerEntity = initPlayer();
        mockMvc.
                perform(post("/team/squad/edit/" + playerEntity.getId())
                        .param("firstName", UPDATED_FIRST_NAME)
                        .param("lastName", UPDATED_LAST_NAME)
                        .param("squadNumber", String.valueOf(UPDATED_SQUAD_NUMBER))
                        .param("position", String.valueOf(UPDATED_POSITION))
                        .param("age", String.valueOf(UPDATED_AGE))
                        .param("nationality", String.valueOf(UPDATED_NATIONALITY))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isForbidden());

    }
    @WithMockUser(value = "stefan", roles = {"ADMIN", "USER"})
    @Test
    void postEditPlayerInvalidInput() throws Exception {
        PlayerEntity playerEntity = initPlayer();
        mockMvc.
                perform(post("/team/squad/edit/" + playerEntity.getId())
                        .param("firstName", UPDATED_FIRST_NAME)
                        .param("lastName", UPDATED_LAST_NAME)
                        .param("squadNumber", String.valueOf(UPDATED_SQUAD_NUMBER))
                        .param("position", String.valueOf(UPDATED_POSITION))
                        .param("age", String.valueOf(-1))
                        .param("nationality", String.valueOf(UPDATED_NATIONALITY))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection());


        Optional<PlayerEntity> playerEntityBySquadNumber =
                playerRepository.findById(playerEntity.getId());
        Assertions.assertTrue(playerEntityBySquadNumber.isPresent());
        Assertions.assertEquals(playerEntityBySquadNumber.get().getFirstName(), FIRST_NAME);
        Assertions.assertEquals(playerEntityBySquadNumber.get().getLastName(), LAST_NAME);
        Assertions.assertEquals(playerEntityBySquadNumber.get().getSquadNumber(), SQUAD_NUMBER);
        Assertions.assertEquals(playerEntityBySquadNumber.get().getAge(), AGE);
        Assertions.assertEquals(playerEntityBySquadNumber.get().getPosition(), POSITION);
        Assertions.assertEquals(playerEntityBySquadNumber.get().getNationality(), NATIONALITY);

    }
    @WithMockUser(value = "stefan", roles = {"ADMIN", "USER"})
    @Test
    void postEditPlayerSquadNumberTaken() throws Exception {

       initPlayer();
        PlayerEntity playerEntity1 = initPlayer2();
        mockMvc.
                perform(post("/team/squad/edit/" + playerEntity1.getId())
                        .param("firstName", UPDATED_FIRST_NAME)
                        .param("lastName", UPDATED_LAST_NAME)
                        .param("squadNumber", String.valueOf(SQUAD_NUMBER))
                        .param("position", String.valueOf(UPDATED_POSITION))
                        .param("age", String.valueOf(UPDATED_AGE))
                        .param("nationality", String.valueOf(UPDATED_NATIONALITY))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection()).andExpect(flash().attribute("squadNumberTaken", true));

        Optional<PlayerEntity> playerEntityBySquadNumber = playerRepository.findPlayerEntityBySquadNumber(UPDATED_SQUAD_NUMBER);
        Assertions.assertTrue(playerEntityBySquadNumber.isPresent());

    }
    @WithMockUser(value = "stefan", roles = {"ADMIN", "USER"})
    @Test
    void deletePlayerAuthorized() throws Exception {

        PlayerEntity playerEntity = initPlayer();
        mockMvc.perform(delete("/team/squad/delete/" + playerEntity.getId()).with(csrf()))
                .andExpect(status().is3xxRedirection());
        Assertions.assertEquals(playerRepository.count(), 0);
    }
    @WithMockUser(value = "stefan", roles = {"ADMIN", "USER"})
    @Test
    void deleteInvalidId() throws Exception {

        PlayerEntity playerEntity = initPlayer();
            mockMvc.perform(delete("/team/squad/delete/" + (playerEntity.getId() + 100)).with(csrf()))
                .andExpect(status().isNotFound()).andExpect(view().name("player-not-found"));
        Assertions.assertEquals(playerRepository.count(), 1);
    }
    @WithMockUser(value = "stefan", roles = {"USER"})
    @Test
    void deleteArticleUnauthorized() throws Exception {
        PlayerEntity playerEntity = initPlayer();
        mockMvc.perform(delete("/team/squad/delete/" + playerEntity.getId()).with(csrf()))
                .andExpect(status().isForbidden());
        Assertions.assertEquals(playerRepository.count(), 1);
    }

    @WithMockUser(value = "stefan", roles = {"USER"})
    @Test
    void getStandings() throws Exception {
        mockMvc.perform(get("/team/standings"))
                .andExpect(status().isOk())
                .andExpect(view().name("standings"));
    }

    @WithMockUser(value = "stefan", roles = {"USER"})
    @Test
    void getMatches() throws Exception {
        mockMvc.perform(get("/team/matches"))
                .andExpect(status().isOk())
                .andExpect(view().name("matches"));
    }


    PlayerEntity initPlayer() {
        PlayerEntity playerEntity = new PlayerEntity()
                .setFirstName(FIRST_NAME).setLastName(LAST_NAME)
                .setSquadNumber(SQUAD_NUMBER).setPosition(POSITION)
                .setAge(AGE).setNationality(NATIONALITY);

        return playerRepository.save(playerEntity);

    }
    PlayerEntity initPlayer2() {
        PlayerEntity playerEntity = new PlayerEntity()
                .setFirstName(FIRST_NAME).setLastName(LAST_NAME)
                .setSquadNumber(UPDATED_SQUAD_NUMBER).setPosition(POSITION)
                .setAge(AGE).setNationality(NATIONALITY);

        return playerRepository.save(playerEntity);

    }


}