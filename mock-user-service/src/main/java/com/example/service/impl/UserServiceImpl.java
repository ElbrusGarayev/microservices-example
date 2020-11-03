package com.example.service.impl;

import com.example.model.User;
import com.example.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void delete(int id) {
        users.removeIf(user -> user.getId() == id);
    }

    @Override
    public void update(int id, User u) {
        users.stream().filter(user -> user.getId() == id).peek(user -> updateFields(user, u)).findFirst();
    }

    public User updateFields(User oldUser, User newUser){
        oldUser.setId(newUser.getId());
        oldUser.setName(newUser.getName());
        oldUser.setAge(newUser.getAge());
        oldUser.setSalary(newUser.getSalary());
        return oldUser;
    }
}
