package com.example.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.model.User;
import com.example.service.UserService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

@WebMvcTest(UserController.class)
@RunWith(SpringRunner.class)
class UserControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    ObjectMapper mapper;

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

        mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*", isA(ArrayList.class)))
                .andExpect(jsonPath("$.*", hasSize(2)));
    }

    @Test
    void findOneUserValidIdThenSuccess() throws Exception {
        when(userService.get(anyLong())).thenReturn(user);

        mockMvc.perform(get("/user")
                .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(1)))
                .andExpect(jsonPath("name", is("Same")))
                .andExpect(jsonPath("age", is(19)))
                .andExpect(jsonPath("salary", is(300)));
    }

    @Test
    void findOneUserInvalidIdThenFailed() throws Exception {
        when(userService.get(anyLong())).thenThrow(NullPointerException.class);

        mockMvc.perform(get("/user")
                .param("id", "15"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void saveUserValidUserThenSuccess() throws Exception {
        when(userService.save(any())).thenReturn(user);

        mockMvc.perform(post("/user-save")
                .contentType(APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(givenObjectWhenConvertJsonThenSuccess(user))
                .accept(MediaType.ALL))
                .andExpect(status().isOk())
                .andExpect(content().json(String.format("{'message':%s}", "Success")));
    }

    @Test
    void saveUserInvalidParametersThenFailed() throws Exception {
        mockMvc.perform(post("/user-save")
                .contentType(APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(givenObjectWhenConvertJsonThenSuccess(null))
                .accept(MediaType.ALL))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteUserValidIsThenSuccess() throws Exception {
        when(userService.delete(anyLong())).thenReturn("Success");

        mockMvc.perform(post("/user-delete")
                .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Success"));
    }

    @Test
    void deleteUserInValidIdThenFailed() throws Exception {
        when(userService.delete(anyLong())).thenThrow(NullPointerException.class);

        mockMvc.perform(post("/user-delete")
                .param("id", "100"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateUserValidUserUpdate() throws Exception {
        when(userService.delete(anyLong())).thenReturn("Success");

        mockMvc.perform(post("/user-update")
                .contentType(APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("searchId", "1")
                .content(givenObjectWhenConvertJsonThenSuccess(user))
                .accept(MediaType.ALL))
                .andExpect(status().isOk())
                .andExpect(content().json(String.format("{'message':%s}", "Success")));
    }

    @Test
    void updateUserInvalidParametersUserUpdate() throws Exception {
        mockMvc.perform(post("/user-update")
                .contentType(APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(givenObjectWhenConvertJsonThenSuccess(null))
                .accept(MediaType.ALL))
                .andExpect(status().isBadRequest());
    }

    private String givenObjectWhenConvertJsonThenSuccess(Object obj) throws JsonProcessingException {
        return mapper.writeValueAsString(obj);
    }
}
