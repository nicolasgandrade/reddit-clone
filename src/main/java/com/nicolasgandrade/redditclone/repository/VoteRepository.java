package com.nicolasgandrade.redditclone.repository;

import com.nicolasgandrade.redditclone.model.Post;
import com.nicolasgandrade.redditclone.model.User;
import com.nicolasgandrade.redditclone.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
}
