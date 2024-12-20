package com.example.socialMediaApp.controller;

import com.example.socialMediaApp.model.Post;
import com.example.socialMediaApp.model.User;
import com.example.socialMediaApp.services.PostService;
import com.example.socialMediaApp.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;
    private final UserService userService;

    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @PostMapping
    public Post createPost(@RequestParam Long userId, @RequestBody Post post) {
        User user = userService.findById(userId);
        post.setUser(user);
        return postService.savePost(post);
    }

    @GetMapping("/{userId}")
    public List<Post> getUserPosts(@PathVariable Long userId) {
        User user = userService.findById(userId);
        return postService.getPostsByUser(user);
    }

    @GetMapping("/{userId}/feed")
    public List<Post> getUserFeed(@PathVariable Long userId) {
        User user = userService.findById(userId);
        return postService.getFeed(user);
    }

}
