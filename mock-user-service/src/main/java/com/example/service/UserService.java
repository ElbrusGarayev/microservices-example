package com.example.service;

import com.example.model.User;

import java.util.List;

public interface UserService {

    User save(User user);

    User get(int id);

    List<User> getAll();

    User delete(int id);

    User update(int id, User user);
}
