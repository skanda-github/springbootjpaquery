package com.example.springbootjpaquery.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.springbootjpaquery.entity.User;
import com.example.springbootjpaquery.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

     public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public List<User> searchUsersByName(String keyword) {
        return userRepository.searchByNameContaining(keyword);
    }

    public List<User> getUsersOlderThan(int age) {
        return userRepository.findUsersOlderThan(age);
    }

}
