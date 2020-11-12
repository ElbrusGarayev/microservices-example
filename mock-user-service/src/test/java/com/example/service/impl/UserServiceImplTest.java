package com.example.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.example.model.User;
import com.example.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@WebMvcTest(UserServiceImpl.class)
@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    @MockBean
    private UserRepository userRepository;

    private User user;
    private User user1;

    @BeforeEach
    public void setUp() {
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

        userRepository.save(user);
    }

    @Test
    public void testSave() {
        when(userRepository.save(any())).thenReturn(user);

        User newUser = userRepository.save(user);
        assertThat(newUser.equals(user));
    }

    @Test
    public void testGet() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.ofNullable(user));

        User currentUser = userRepository.findById(1L).orElseThrow(null);
        assertThat(currentUser.equals(user));
    }

    @Test
    public void testGetAll() {
        when(userRepository.findAll()).thenReturn(Stream.of(new User(1, "John", 20, 2000),
                new User(2, "Bill", 30, 3000)).collect(Collectors.toList()));

        List<User> users = userRepository.findAll();
        assertThat(users.size() == 2);
    }

    @Test
    public void testUpdate() {
        when(userRepository.save(any())).thenReturn(user1);

        User updatedUser = userRepository.save(user1);
        assertThat(updatedUser.equals(user1));
    }
}
