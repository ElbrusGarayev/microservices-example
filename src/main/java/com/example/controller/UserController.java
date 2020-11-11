package com.example.controller;

import com.example.dto.RestResponseDTO;
import com.example.model.User;
import com.example.service.UserService;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private static final String MESSAGE = "Success";

    @GetMapping("users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("user")
    public ResponseEntity<User> get(int id) {
        return ResponseEntity.ok(userService.get(id));
    }

    @PostMapping("user-save")
    public ResponseEntity<User> save(User user) {
        return ResponseEntity.ok(userService.save(user));
    }

    @PostMapping("user-delete")
    public ResponseEntity<String> delete(int id) {
        String response = userService.delete(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("user-update")
    public ResponseEntity<RestResponseDTO> update(int searchId, User user) {
        return ResponseEntity.ok(new RestResponseDTO(userService.update(searchId, user), MESSAGE));
    }
}
