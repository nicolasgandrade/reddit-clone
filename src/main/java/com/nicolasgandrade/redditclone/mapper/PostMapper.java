package com.nicolasgandrade.redditclone.mapper;

import com.github.marlonlom.utilities.timeago.TimeAgo;
import com.nicolasgandrade.redditclone.dto.PostRequest;
import com.nicolasgandrade.redditclone.dto.PostResponse;
import com.nicolasgandrade.redditclone.model.Post;
import com.nicolasgandrade.redditclone.model.Subreddit;
import com.nicolasgandrade.redditclone.model.User;
import com.nicolasgandrade.redditclone.repository.CommentRepository;
import com.nicolasgandrade.redditclone.repository.VoteRepository;
import com.nicolasgandrade.redditclone.service.AuthService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Aqui foi usada a classe abstrata em vez de interface porque
 * outros atributos que não estão sendo mapeados foram implementados
 * na classe PostResponse.
 */
@Mapper(componentModel = "spring")
public abstract class PostMapper {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private AuthService authService;

    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "subreddit", source = "subreddit")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "description", source = "postRequest.description")
    @Mapping(target = "voteCount", constant = "0")
    public abstract Post map(PostRequest postRequest, Subreddit subreddit, User user);

    @Mapping(target = "id", source = "postId")
    @Mapping(target = "postName", source = "postName")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "url", source = "url")
    @Mapping(target = "subredditName", source = "subreddit.name")
    @Mapping(target = "userName", source = "user.username")
    @Mapping(target = "commentCount", expression = "java(commentCount(post))")
    @Mapping(target = "duration", expression = "java(getDuration(post))")
    public abstract PostResponse mapToDto(Post post);

    Integer commentCount(Post post) {
        return commentRepository.findByPost(post).size();
    }

    String getDuration(Post post) {
        return TimeAgo.using(post.getCreatedDate().toEpochMilli());
    }
}
