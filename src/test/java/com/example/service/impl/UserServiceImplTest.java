package com.example.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.example.client.UserClient;
import com.example.model.User;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@WebMvcTest(UserServiceImplTest.class)
@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    @MockBean
    UserClient userClient;

    private User user;
    private User user1;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .id(1)
                .name("Same")
                .age(19)
                .salary(300)
                .build();

        user1 = User.builder()
                .id(1)
                .name("John")
                .age(20)
                .salary(500)
                .build();

        userClient.save(user);
    }

    @Test
    void getUsers() {
        when(userClient.getUsers()).thenReturn(Stream.of(new User(1, "John", 20, 2000),
                new User(2, "Bill", 30, 3000)).collect(Collectors.toList()));

        List<User> users = userClient.getUsers();
        assertThat(users.size() == 2);
    }

    @Test
    void testGet() {
        when(userClient.get(anyLong())).thenReturn(user);

        User currentUser = userClient.get(1);
        assertThat(currentUser.equals(user));
    }

    @Test
    void testSave() {
        when(userClient.save(any())).thenReturn(user);

        User newUser = userClient.save(user);
        assertThat(newUser.equals(user));
    }

    @Test
    void testDelete() {
        when(userClient.delete(anyLong())).thenReturn("Success");

        String response = userClient.delete(1);
        assertThat(response.equals("Success"));
    }

    @Test
    void testUpdate() {
        when(userClient.update(any())).thenReturn(user1);

        User updatedUser = userClient.update(user1);
        assertThat(updatedUser.equals(user1));
    }
}
