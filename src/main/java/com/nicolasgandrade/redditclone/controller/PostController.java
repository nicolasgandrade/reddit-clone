package com.nicolasgandrade.redditclone.controller;

import com.nicolasgandrade.redditclone.dto.PostRequest;
import com.nicolasgandrade.redditclone.dto.PostResponse;
import com.nicolasgandrade.redditclone.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity createPost(@RequestBody PostRequest postRequest) {
        postService.save(postRequest);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public PostResponse getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

//    @GetMapping("/by-subreddit/{id}")
//    public List<PostResponse> getPostsBySubreddit(@PathVariable Long id) {
//        return postService.getPostsBySubreddit(id);
//    }
//
//    @GetMapping("/by-username/{username}")
//    public List<PostResponse> getPostsByUsername(@PathVariable String username) {
//        return postService.getPostsByUsername(username);
//    }
}
