package com.example.pathfinder.web;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.pathfinder.model.*;
import com.example.pathfinder.repositories.CommentRepository;
import com.example.pathfinder.repositories.RoleRepository;
import com.example.pathfinder.repositories.RouteRepository;
import com.example.pathfinder.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
@WithMockUser("petkan")
@SpringBootTest
@AutoConfigureMockMvc
public class CommentsRestControllerTest {

    private static final String COMMENT_1 = "something";
    private static final String COMMENT_2 = "something else";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    private User testUser;
    private Role adminRole,userRole;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setPassword("password");
        testUser.setUsername("stefan");
        testUser.setFullName("stefan gulev");
        adminRole = new Role().setRole(RoleEnum.ADMIN);
        userRole = new Role().setRole(RoleEnum.USER);
        Role save = roleRepository.save(userRole);
        Role save1 = roleRepository.save(adminRole);
        testUser.setRoles(Set.of(save, save1));


        testUser = userRepository.save(testUser);
    }

    @AfterEach
    void tearDown() {
        routeRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void testGetComments() throws Exception {
        long routeId = initRoute();
        mockMvc.perform(get("/api/" + routeId + "/comments")
        ).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$.[0].message", is(COMMENT_1))).
                andExpect(jsonPath("$.[1].message", is(COMMENT_2)));
    }

    private long initRoute() {
        Route testRoute = new Route();
        testRoute.setName("Testing route");

        testRoute = routeRepository.save(testRoute);

        Comment comment1 = new Comment();
        comment1.setCreated(LocalDateTime.now());
        comment1.setAuthor(testUser);
        comment1.setTextContent(COMMENT_1);
        comment1.setApproved(true);
        comment1.setRoute(testRoute);


        Comment comment2 = new Comment();
        comment2.setCreated(LocalDateTime.now());
        comment2.setAuthor(testUser);
        comment2.setTextContent(COMMENT_2);
        comment2.setApproved(true);
        comment2.setRoute(testRoute);

        testRoute.setComments(Set.of(comment1, comment2));

        return routeRepository.save(testRoute).getId();
    }
}
