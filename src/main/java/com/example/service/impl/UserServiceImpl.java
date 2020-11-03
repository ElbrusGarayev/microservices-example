package com.example.service.impl;

import com.example.client.UserClient;
import com.example.model.User;
import com.example.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserClient userClient;

    @Override
    public List<User> getUsers() {
        List<User> users = userClient.getUsers();
        return users;
    }

    @Override
    public User get(int id) {
        return userClient.get(id);
    }

    @Override
    public User save(User user) {
        return userClient.save(user);
    }

    @Override
    public void delete(int id) {
        userClient.delete(id);
    }

    @Override
    public void update(int searchId, User user) {
        userClient.update(searchId, user);
    }
}
