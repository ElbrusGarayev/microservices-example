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

    @GetMapping("users")
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("user")
    public User get(int id) {
        return userService.get(id);
    }

    @PostMapping("user-new")
    public User save(@RequestBody User user) {
        return userService.save(user);
    }

    @PostMapping("user-delete")
    public void delete(int id) {
        userService.delete(id);
    }

    @PostMapping("user-update")
    public User update(int searchId, @RequestBody User user) {
        return userService.update(searchId, user);
    }
}
