package com.example.service.impl;

import com.example.client.UserClient;
import com.example.model.User;
import com.example.service.UserService;

import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserClient userClient;

    @Override
    public List<User> getUsers() {
        return userClient.getUsers();
    }

    @Override
    public User get(long id) {
        return userClient.get(id);
    }

    @Override
    public User save(User user) {
        return userClient.save(user);
    }

    @Override
    public String delete(long id) {
        return userClient.delete(id);
    }

    @Override
    public User update(User user) {
        return userClient.update(user);
    }
}
