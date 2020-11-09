package com.example.service;

import com.example.model.User;

import java.util.List;

public interface UserService {

    List<User> getUsers();

    User get(int id);

    User save(User user);

    void delete(int id);

    void update(int searchId, User user);

}
