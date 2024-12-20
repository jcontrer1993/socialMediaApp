package com.example.socialMediaApp.controller;

import com.example.socialMediaApp.model.User;
import com.example.socialMediaApp.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User registerUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PostMapping("/{userId}/follow/{followId}")
    public void followUser(@PathVariable Long userId, @PathVariable Long followId) {
        userService.followUser(userId, followId);
    }

    @GetMapping("/{userId}/following")
    public List<User> getFollowing(@PathVariable Long userId) {
        return userService.getFollowing(userId);
    }
}
