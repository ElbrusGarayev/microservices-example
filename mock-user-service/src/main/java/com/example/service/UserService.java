package com.example.service;

import com.example.model.User;

import java.util.List;

public interface UserService {

    User save(User user);

    User get(int id);

    List<User> getAll();

    void delete(int id);

    void update(int id, User user);
}
