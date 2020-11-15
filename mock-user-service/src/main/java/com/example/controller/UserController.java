package com.example.controller;

import com.example.exception.ApiRequestException;
import com.example.model.User;
import com.example.service.UserService;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user/api")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final String USER_NOT_FOUND = "User not found";
    private final String SOMETHING_WENT_WRONG = "Something went wrong";

    @GetMapping("users")
    public List<User> getAll() {
        try {
            return userService.getAll();
        } catch (Exception e) {
            throw new ApiRequestException(SOMETHING_WENT_WRONG);
        }
    }

    @GetMapping("user")
    public User get(@RequestParam long id) {
        return userService.get(id).orElseThrow(() -> new ApiRequestException(USER_NOT_FOUND));
    }

    @PostMapping("user-save")
    public User save(@RequestBody User user) {
        try {
            return userService.save(user);
        } catch (Exception e) {
            throw new ApiRequestException(SOMETHING_WENT_WRONG);
        }
    }

    @PostMapping("user-delete")
    public String delete(@RequestParam long id) {
        try {
            return userService.delete(id);
        } catch (Exception e) {
            throw new ApiRequestException(USER_NOT_FOUND);
        }
    }

    @PostMapping("user-update")
    public User update(@RequestBody User user) {
        try {
            return userService.update(user);
        } catch (Exception e) {
            throw new ApiRequestException(SOMETHING_WENT_WRONG);
        }
    }
}
