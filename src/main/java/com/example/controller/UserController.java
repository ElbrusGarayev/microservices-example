package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final String SUCCESS = "Success";

    @GetMapping("all")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("get")
    public ResponseEntity<User> get(int id){
        return ResponseEntity.ok(userService.get(id));
    }

    @PostMapping("save")
    public ResponseEntity<User> save(User user){
        return ResponseEntity.ok(userService.save(user));
    }

    @PostMapping("delete")
    public ResponseEntity<String> delete(int id){
        try{
            userService.delete(id);
            return ResponseEntity.ok(SUCCESS);
        }catch (Exception ex){
            return ResponseEntity.ok("");
        }
    }

    @PostMapping("update")
    public ResponseEntity<String> update(int searchId, User user){
        try{
            userService.update(searchId, user);
            return ResponseEntity.ok(SUCCESS);
        }catch (Exception ex){
            return ResponseEntity.ok("");
        }
    }
}
