package com.example.service;

import com.example.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getUsers();

    User get(long id);

    User save(User user);

    String delete(long id);

    User update(User user);

}
