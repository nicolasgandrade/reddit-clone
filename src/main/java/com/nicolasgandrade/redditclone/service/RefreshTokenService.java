package com.nicolasgandrade.redditclone.service;

import com.nicolasgandrade.redditclone.exceptions.SpredditException;
import com.nicolasgandrade.redditclone.model.RefreshToken;
import com.nicolasgandrade.redditclone.repository.RefreshTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken generateRefreshToken() {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setCreatedDate(Instant.now());
        return refreshTokenRepository.save(refreshToken);
    }

    void validateRefreshToken(String token) {
        refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new SpredditException("Invalid refresh token"));
    }

    @Transactional
    public void deleteRefreshToken(String token) {
         refreshTokenRepository.deleteByToken(token);
    }
}
