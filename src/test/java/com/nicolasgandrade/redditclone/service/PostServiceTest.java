package com.nicolasgandrade.redditclone.service;

import com.nicolasgandrade.redditclone.dto.PostRequest;
import com.nicolasgandrade.redditclone.dto.PostResponse;
import com.nicolasgandrade.redditclone.mapper.PostMapper;
import com.nicolasgandrade.redditclone.model.Post;
import com.nicolasgandrade.redditclone.model.Subreddit;
import com.nicolasgandrade.redditclone.model.User;
import com.nicolasgandrade.redditclone.repository.PostRepository;
import com.nicolasgandrade.redditclone.repository.SubredditRepository;
import com.nicolasgandrade.redditclone.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Optional;

import static java.util.Collections.emptyList;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    @Mock
    private PostRepository postRepository;
    @Mock
    private SubredditRepository subredditRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private AuthService authService;
    @Mock
    private PostMapper postMapper;

    @Captor
    private ArgumentCaptor<Post> postArgumentCaptor;

    private PostService postService;

    @BeforeEach
    public void setup() {
        postService = new PostService(postRepository, subredditRepository, postMapper, authService,userRepository);
    }

    @Test
    @DisplayName("Should find post by id")
    public void shouldFindPostById() {
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

    @Test
    @DisplayName("Should Save Posts")
    public void shouldSavePosts() {
        User currentUser = new User(123L, "test user", "secret password", "user@email.com", Instant.now(), true);
        Subreddit subreddit = new Subreddit(123L, "First Subreddit", "Subreddit Description", emptyList(), Instant.now(), currentUser);
        Post post = new Post(123L, "First Post", "http://url.site", "Test",
                0, null, Instant.now(), null);
        PostRequest postRequest = new PostRequest(null, "First Subreddit", "First Post", "http://url.site", "Test");

        //Mock
        Mockito.when(subredditRepository.findByName("First Subreddit"))
                .thenReturn(Optional.of(subreddit));
        Mockito.when(authService.getCurrentUser())
                .thenReturn(currentUser);
        Mockito.when(postMapper.map(postRequest, subreddit, currentUser))
                .thenReturn(post);

        //Call the service to save a postRequest
        postService.save(postRequest);
        //Verify if the post repository was called 1 time by the postService
        Mockito.verify(postRepository, Mockito.times(1)).save(postArgumentCaptor.capture());

        //Check by asserting if the post was saved;
        Assertions.assertThat(postArgumentCaptor.getValue().getPostId()).isEqualTo(123L);
        Assertions.assertThat(postArgumentCaptor.getValue().getPostName()).isEqualTo("First Post");
    }
}