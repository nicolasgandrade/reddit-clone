package com.nicolasgandrade.redditclone.service;

import com.nicolasgandrade.redditclone.dto.PostResponse;
import com.nicolasgandrade.redditclone.mapper.PostMapper;
import com.nicolasgandrade.redditclone.model.Post;
import com.nicolasgandrade.redditclone.repository.PostRepository;
import com.nicolasgandrade.redditclone.repository.SubredditRepository;
import com.nicolasgandrade.redditclone.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class PostServiceTest {

    private PostRepository postRepository = Mockito.mock(PostRepository.class);
    private SubredditRepository subredditRepository = Mockito.mock(SubredditRepository.class);
    private UserRepository userRepository = Mockito.mock(UserRepository.class);
    private AuthService authService = Mockito.mock(AuthService.class);
    private PostMapper postMapper = Mockito.mock(PostMapper.class);

    @Test
    @DisplayName("Should find post by id")
    void shouldFindPostById() {
        PostService postService = new PostService(
                postRepository, subredditRepository, postMapper, authService,userRepository
        );
        Post post = new Post(123L, "Test Post", "https://www.google.com", "Test",
                0, null, Instant.now(), null);
        PostResponse expectedPostResponse = new PostResponse(123L, "Test Post", "www.google.com", "Test",
                "testuser4", "testsubreddit", 0, 0, "1 hora atrás");

        Mockito.when(postRepository.findById(123L)).thenReturn(Optional.of(post));
        Mockito.when(postMapper.mapToDto(Mockito.any(Post.class))).thenReturn(expectedPostResponse);

        PostResponse actualPostResponse = postService.getPost(123L);

        Assertions.assertThat(actualPostResponse.getId()).isEqualTo(expectedPostResponse.getId());
        Assertions.assertThat(actualPostResponse.getPostName()).isEqualTo(expectedPostResponse.getPostName());
    }
}