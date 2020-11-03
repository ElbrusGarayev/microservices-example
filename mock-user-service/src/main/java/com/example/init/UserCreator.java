package com.example.init;

import com.example.model.User;
import com.example.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class UserCreator {

    private final UserService userService;

    @Bean
    public CommandLineRunner init(){
        return args -> {
            userService.save(new User(1, "John Doe", 20, 1500));
            userService.save(new User(2, "Bill Gates", 50, 4500));
            userService.save(new User(3, "Elon Musk", 30, 2500));
            userService.save(new User(4, "Steven Hawking", 40, 3500));
            userService.save(new User(5, "Emily Muller", 25, 2800));
            userService.save(new User(6, "Paulo Muller", 35, 4300));
        };
    }
}
