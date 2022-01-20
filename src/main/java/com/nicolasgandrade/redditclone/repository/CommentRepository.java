package com.nicolasgandrade.redditclone.repository;

import com.nicolasgandrade.redditclone.model.Comment;
import com.nicolasgandrade.redditclone.model.Post;
import com.nicolasgandrade.redditclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);
    List<Comment> findByUser(User user);
}
