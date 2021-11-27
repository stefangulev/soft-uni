package com.example.pathfinder.web;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.pathfinder.model.*;
import com.example.pathfinder.model.binding.NewCommentBindingModel;
import com.example.pathfinder.repositories.CommentRepository;
import com.example.pathfinder.repositories.RoleRepository;
import com.example.pathfinder.repositories.RouteRepository;
import com.example.pathfinder.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.text.MatchesPattern;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
@WithMockUser("stefan@abv.bg")
@SpringBootTest
@AutoConfigureMockMvc
public class CommentsRestControllerTest {

    private static final String COMMENT_1 = "something";
    private static final String COMMENT_2 = "Comments need a large amount of characters to be submitted!";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    ObjectMapper objectMapper;
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
        testUser.setEmail("stefan@abv.bg");
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
        commentRepository.deleteAll();
    }

    @Test
    void testGetComments() throws Exception {
        Route route = initComments(initRoute());
        mockMvc.perform(get("/api/" + route.getId() + "/comments")
        ).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$.[0].message", is(COMMENT_1))).
                andExpect(jsonPath("$.[1].message", is(COMMENT_2)));
    }

    @Test
    void testCreateComments() throws Exception {
        NewCommentBindingModel testComment = new NewCommentBindingModel()
                .setMessage(COMMENT_2);

        Route emptyRoute = initRoute();
        mockMvc
                .perform(post("/api/" + emptyRoute.getId() + "/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testComment))
                        .accept(MediaType.APPLICATION_JSON)
                        .with(csrf())).andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string("Location", MatchesPattern.matchesPattern("/api/" + emptyRoute.getId() + "/comments/\\d")))
                .andExpect(jsonPath("$.message").value(is(COMMENT_2)));
    }

    private Route initRoute() {
        Route testRoute = new Route();
        testRoute.setName("Testing route");
        return routeRepository.save(testRoute);

    }

    private Route initComments(Route testRoute) throws InterruptedException {

        Comment comment1 = new Comment();
        comment1.setCreated(LocalDateTime.now());
        comment1.setAuthor(testUser);
        comment1.setTextContent(COMMENT_1);
        comment1.setApproved(true);
        comment1.setRoute(testRoute);


        Thread.sleep(1000);

        Comment comment2 = new Comment();
        comment2.setCreated(LocalDateTime.now());
        comment2.setAuthor(testUser);
        comment2.setTextContent(COMMENT_2);
        comment2.setApproved(true);
        comment2.setRoute(testRoute);

        testRoute.setComments(List.of(comment1, comment2));

        return routeRepository.save(testRoute);
    }
}
