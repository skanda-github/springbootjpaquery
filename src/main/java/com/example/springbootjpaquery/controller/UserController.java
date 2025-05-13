package com.example.springbootjpaquery.controller;

import com.example.springbootjpaquery.entity.User;
import com.example.springbootjpaquery.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


     // Create user
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    // Get user by email
    @GetMapping("/email/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    // Search users by name keyword
    @GetMapping("/search")
    public List<User> searchByName(@RequestParam String keyword) {
        return userService.searchUsersByName(keyword);
    }

    // Get users older than a specific age
    @GetMapping("/older-than/{age}")
    public List<User> getUsersOlderThan(@PathVariable int age) {
        return userService.getUsersOlderThan(age);
    }

}
