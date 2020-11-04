package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user/api")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("all")
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("get")
    public User get(int id) {
        return userService.get(id);
    }

    @PostMapping("save")
    public User save(@RequestBody User user) {
        return userService.save(user);
    }

    @PostMapping("delete")
    public void delete(int id) {
        userService.delete(id);
    }

    @PostMapping("update")
    public void update(int searchId, @RequestBody User user) {
        userService.update(searchId, user);
    }
}
