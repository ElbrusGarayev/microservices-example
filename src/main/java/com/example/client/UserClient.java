package com.example.client;


import com.example.model.User;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "UserClient", url = "http://localhost:8081/user/api")
public interface UserClient {

    @GetMapping("/all")
    List<User> getUsers();

    @GetMapping("/get")
    User get(@RequestParam("id") int id);

    @PostMapping("/save")
    User save(@RequestBody User user);

    @PostMapping("/delete")
    void delete(@RequestParam("id") int id);

    @PostMapping("/update")
    void update(@RequestParam("searchId") int searchId, @RequestBody User user);
}
