package com.example.service.impl;

import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.service.UserService;

import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    public UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> get(long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public String delete(long id) {
        userRepository.deleteById(id);
        return "Success";
    }

    @Override
    public User update(User us) {
        return userRepository.save(us);
    }
}
