package com.example.service;

import com.example.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User save(User user);

    Optional<User> get(long id);

    List<User> getAll();

    String delete(long id);

    User update(User user);
}
