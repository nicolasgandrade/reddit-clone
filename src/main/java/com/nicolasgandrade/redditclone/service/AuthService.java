package com.nicolasgandrade.redditclone.service;

import com.nicolasgandrade.redditclone.dto.RegisterRequest;

public interface AuthService {
    void signup(RegisterRequest registerRequest);
}
