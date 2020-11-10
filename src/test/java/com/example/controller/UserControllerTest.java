package com.example.controller;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

import com.example.model.User;
import com.example.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebMvcTest(UserController.class)
@RunWith(SpringRunner.class)
class UserControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    private User user;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(this.webApplicationContext)
                .build();

        user = User.builder()
                .id(1)
                .name("Same")
                .age(19)
                .salary(300)
                .build();
    }

    @Test
    void findAllUserThenSuccess() throws Exception {
        when(userService.getUsers())
                .thenReturn(Stream.of(new User(1, "John", 20, 2000),
                        new User(2, "Bill", 30, 3000)).collect(Collectors.toList()));

        mockMvc.perform(get("/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*", isA(ArrayList.class)))
                .andExpect(jsonPath("$.*", hasSize(2)));
    }

    @Test
    void findOneUserValidIdThenSuccess() throws Exception {
        when(userService.get(anyInt())).thenReturn(user);

        mockMvc.perform(get("/get")
                .param("id", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(1)))
                .andExpect(jsonPath("name", is("Same")))
                .andExpect(jsonPath("age", is(19)))
                .andExpect(jsonPath("salary", is(300)));
    }

    @Test
    void findUserInvalidIdThenFailed() throws Exception {
//        when(userService.get(anyInt())).thenReturn(null);

        mockMvc.perform(get("/get")
                .param("id", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", is("{}")));
    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }
}