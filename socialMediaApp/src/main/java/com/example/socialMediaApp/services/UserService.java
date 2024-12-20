package com.example.socialMediaApp.services;

import com.example.socialMediaApp.UserRepository;
import com.example.socialMediaApp.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found!"));
    }

    public void followUser(Long userId, Long followId) {
        User user = findById(userId);
        User toFollow = findById(followId);

        if (user.getFollowing().contains(toFollow)) {
            throw new RuntimeException("You are already following this user!");
        }

        user.getFollowers().add(toFollow);
        userRepository.save(user);
    }

    // return list of users that are being followed by "user"
    public List<User> getFollowing(Long userId) {
        User user = findById(userId);
        return List.copyOf(user.getFollowing());
    }
}
