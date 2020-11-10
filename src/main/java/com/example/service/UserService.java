package com.example.service;

import com.example.model.User;

import java.util.List;

public interface UserService {

    List<User> getUsers();

    User get(int id);

    User save(User user);

    User delete(int id);

    User update(int searchId, User user);

}
