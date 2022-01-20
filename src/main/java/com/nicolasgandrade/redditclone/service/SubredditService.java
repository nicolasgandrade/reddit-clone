package com.nicolasgandrade.redditclone.service;

import com.nicolasgandrade.redditclone.dto.SubredditDto;
import com.nicolasgandrade.redditclone.exceptions.SpredditException;
import com.nicolasgandrade.redditclone.mapper.SubredditMapper;
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
    private final SubredditMapper subredditMapper;

    @Transactional
    public SubredditDto save(SubredditDto subredditDto) {
        Subreddit savedObj = subredditRepository.save(subredditMapper.mapDtoToSubreddit(subredditDto));
        subredditDto.setId(savedObj.getId());
        return subredditDto;
    }

    @Transactional
    public List<SubredditDto> getAll() {
        return subredditRepository.findAll()
                .stream()
                .map(subredditMapper::mapSubredditToDto)
                .collect(Collectors.toList());
    }

    public SubredditDto getSubreddit(Long id) {
        Subreddit subreddit = subredditRepository.findById(id).orElseThrow(() -> new SpredditException("Id not found"));
        return subredditMapper.mapSubredditToDto(subreddit);
    }



//    private SubredditDto mapToDto(Subreddit subreddit) {
//        return SubredditDto.builder()
//                .id(subreddit.getId())
//                .name(subreddit.getName())
//                .numberOfPosts(subreddit.getPosts().size())
//                .build();
//    }
//
//    private Subreddit mapSubredditDto(SubredditDto subredditDto) {
//        return Subreddit.builder()
//                .name(subredditDto.getName())
//                .description(subredditDto.getDescription())
//                .build();

//    }
}
