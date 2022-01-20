package com.nicolasgandrade.redditclone.service;

import com.nicolasgandrade.redditclone.dto.CommentDto;
import com.nicolasgandrade.redditclone.exceptions.PostNotFoundException;
import com.nicolasgandrade.redditclone.mapper.CommentMapper;
import com.nicolasgandrade.redditclone.model.Comment;
import com.nicolasgandrade.redditclone.model.Post;
import com.nicolasgandrade.redditclone.model.User;
import com.nicolasgandrade.redditclone.repository.CommentRepository;
import com.nicolasgandrade.redditclone.repository.PostRepository;
import com.nicolasgandrade.redditclone.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final AuthServiceImpl authService;
    private final CommentMapper commentMapper;

    public void save(CommentDto commentDto) {
        Post post = postRepository.findById(commentDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException(commentDto.getPostId().toString()));
        Comment comment = commentMapper.map(commentDto, post, authService.getCurrentUser());
        commentRepository.save(comment);
    }

    public List<CommentDto> getAllCommentsFromPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId.toString()));
        return commentRepository.findByPost(post)
                .stream()
                .map(commentMapper::mapToDto).collect(Collectors.toList());
    }

    public List<CommentDto> getAllCommentsFromUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return commentRepository.findByUser(user)
                .stream().map(commentMapper::mapToDto).collect(Collectors.toList());
    }
}
