package com.nicolasgandrade.redditclone.service;

import com.nicolasgandrade.redditclone.dto.PostRequest;
import com.nicolasgandrade.redditclone.dto.PostResponse;
import com.nicolasgandrade.redditclone.exceptions.PostNotFoundException;
import com.nicolasgandrade.redditclone.exceptions.SubredditNotFoundException;
import com.nicolasgandrade.redditclone.mapper.PostMapper;
import com.nicolasgandrade.redditclone.model.Post;
import com.nicolasgandrade.redditclone.model.Subreddit;
import com.nicolasgandrade.redditclone.repository.PostRepository;
import com.nicolasgandrade.redditclone.repository.SubredditRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final SubredditRepository subredditRepository;
    private final PostMapper postMapper;
    private final AuthServiceImpl authService;

    public void save(PostRequest postRequest) {
        Subreddit subreddit = subredditRepository.findByName(postRequest.getSubredditName())
                .orElseThrow(() -> new SubredditNotFoundException(postRequest.getSubredditName()));
        postRepository.save(postMapper.map(postRequest, subreddit, authService.getCurrentUser()));
    }

    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id.toString()));
        return postMapper.mapToDto(post);
    }


}
