package com.example.controller;

import com.example.dto.RestResponseDTO;
import com.example.exception.ApiRequestException;
import com.example.model.User;
import com.example.service.UserService;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private static final String MESSAGE = "Success";
    private final String SOMETHING_WENT_WRONG = "Something went wrong";
    private final String USER_NOT_FOUND = "User not found";

    @GetMapping("users")
    public ResponseEntity<RestResponseDTO<List<User>>> getUsers() {
        try {
            return ResponseEntity.ok(new RestResponseDTO<>(userService.getUsers(), MESSAGE));
        } catch (Exception e) {
            throw new ApiRequestException(SOMETHING_WENT_WRONG);
        }
    }

    @GetMapping("user")
    public ResponseEntity<User> get(@RequestParam long id) {
        try{
            return ResponseEntity.ok(userService.get(id));
        } catch (Exception e){
            throw new ApiRequestException(USER_NOT_FOUND);
        }
    }

    @PostMapping("user-save")
    public ResponseEntity<RestResponseDTO<User>> save(@RequestBody User user) {
        try {
            return ResponseEntity.ok(new RestResponseDTO<>(userService.save(user), MESSAGE));
        } catch (Exception e) {
            throw new ApiRequestException(SOMETHING_WENT_WRONG);
        }
    }

    @PostMapping("user-delete")
    public ResponseEntity<String> delete(long id) {
        try {
            String response = userService.delete(id);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new ApiRequestException(USER_NOT_FOUND);
        }
    }

    @PostMapping("user-update")
    public ResponseEntity<RestResponseDTO<User>> update(@RequestBody User user) {
        try {
            return ResponseEntity.ok(new RestResponseDTO<>(userService.update(user), MESSAGE));
        } catch (Exception e) {
            throw new ApiRequestException(SOMETHING_WENT_WRONG);
        }
    }
}
