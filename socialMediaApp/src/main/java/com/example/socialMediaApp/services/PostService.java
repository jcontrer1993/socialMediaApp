package com.example.socialMediaApp.services;

import com.example.socialMediaApp.PostRepository;
import com.example.socialMediaApp.model.Post;
import com.example.socialMediaApp.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    public List<Post> getPostsByUser(User user) {
        return postRepository.findByUserId(user.getId());
    }

    // find feed (Posts) for users that are being followed by "user"
    public List<Post> getFeed(User user) {
        return user.getFollowing().stream().flatMap(following -> postRepository.findByUserId(following.getId()).stream()).collect(Collectors.toList());
    }
}
