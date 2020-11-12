package com.example.client;

import com.example.model.User;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "UserClient", url = "http://localhost:8081/user/api")
public interface UserClient {

    @GetMapping("/users")
    List<User> getUsers();

    @GetMapping("/user")
    User get(@RequestParam("id") long id);

    @PostMapping("/user-save")
    User save(@RequestBody User user);

    @PostMapping("/user-delete")
    String delete(@RequestParam("id") long id);

    @PostMapping("/user-update")
    User update(@RequestBody User user);
}
