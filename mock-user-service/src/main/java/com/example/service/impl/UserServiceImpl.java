package com.example.service.impl;

import com.example.model.User;
import com.example.service.UserService;

import java.util.List;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    public List<User> users;

    @Override
    public User save(User user) {
        users.add(user);
        return user;
    }

    @Override
    public User get(int id) {
        return users.stream().filter(user -> user.getId() == id).findAny().orElse(null);
    }

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public User delete(int id) {
        User deletedUser = get(id);
        users.removeIf(user -> user.getId() == id);
        return deletedUser;
    }

    @Override
    public User update(int id, User us) {
        return users.stream().filter(user -> user.getId() == id)
                .peek(user -> updateFields(user, us))
                .findFirst().orElseThrow(null);
    }

    public User updateFields(User oldUser, User newUser) {
        oldUser.setId(newUser.getId());
        oldUser.setName(newUser.getName());
        oldUser.setAge(newUser.getAge());
        oldUser.setSalary(newUser.getSalary());
        return oldUser;
    }
}
