package com.example.service.impl;

import static com.example.util.StaticVariable.MESSAGE;

import com.example.client.UserClient;
import com.example.model.User;
import com.example.service.UserService;

import java.util.List;

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
    public User get(int id) {
        return userClient.get(id);
    }

    @Override
    public User save(User user) {
        return userClient.save(user);
    }

    @Override
    public String delete(int id) {
        userClient.delete(id);
        return MESSAGE;
    }

    @Override
    public User update(int searchId, User user) {
        return userClient.update(searchId, user);
    }
}
