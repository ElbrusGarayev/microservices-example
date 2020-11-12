package com.example.service;

import com.example.model.User;

import java.util.List;

public interface UserService {

    User save(User user);

    User get(long id);

    List<User> getAll();

    String delete(long id);

    User update(User user);
}
