package com.nicolasgandrade.redditclone.service;

import com.nicolasgandrade.redditclone.dto.SubredditDto;
import com.nicolasgandrade.redditclone.model.Subreddit;
import com.nicolasgandrade.redditclone.repository.SubredditRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class SubredditService {

    private final SubredditRepository subredditRepository;

    @Transactional
    public SubredditDto save(SubredditDto subredditDto) {
        Subreddit savedObj = subredditRepository.save(mapSubredditDto(subredditDto));
        subredditDto.setId(savedObj.getId());
        return subredditDto;
    }

    @Transactional
    public List<SubredditDto> getAll() {
        return subredditRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private SubredditDto mapToDto(Subreddit subreddit) {
        return SubredditDto.builder()
                .id(subreddit.getId())
                .subredditName(subreddit.getName())
                .numberOfPosts(subreddit.getPosts().size())
                .build();
    }

    private Subreddit mapSubredditDto(SubredditDto subredditDto) {
        return Subreddit.builder()
                .name(subredditDto.getSubredditName())
                .description(subredditDto.getDescription())
                .build();
    }
}
