package com.example.service;

import com.example.client.UserClient;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@WebMvcTest(UserServiceImplTest.class)
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @MockBean
    UserClient userClient;

    @Test
    void getUsers() {

    }

    @Test
    void get() {
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