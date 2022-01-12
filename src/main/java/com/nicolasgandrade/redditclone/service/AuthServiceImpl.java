package com.nicolasgandrade.redditclone.service;

import com.nicolasgandrade.redditclone.dto.RegisterRequest;
import com.nicolasgandrade.redditclone.exceptions.SpredditException;
import com.nicolasgandrade.redditclone.model.NotificationEmail;
import com.nicolasgandrade.redditclone.model.User;
import com.nicolasgandrade.redditclone.model.VerificationToken;
import com.nicolasgandrade.redditclone.repository.UserRepository;
import com.nicolasgandrade.redditclone.repository.VerificationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthServiceImpl {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;

    @Transactional
    public void signup(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreated(Instant.now());
        user.setEnabled(false);
        userRepository.save(user);

        String token = generateVerificationToken(user);

        String url = "http://localhost:8080/api/auth/accountVerification/";

        mailService.sendMail(new NotificationEmail("Activate your Spreddit account",
                user.getEmail(),
                "Thanks for using Spreddit! " +
                "Please, activate your account to start browsing clocking in the following link: " +
                url + token));
    }

    private String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);

        verificationTokenRepository.save(verificationToken);
        return token;
    }

    public void verifyAccount(String token) {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        verificationToken.orElseThrow(() -> new SpredditException("Invalid token"));
        fetchUserAndEnable(verificationToken.get());
    }

    private void fetchUserAndEnable(VerificationToken verificationToken) {
        @NotBlank(message = "Username is required") String username = verificationToken.getUser().getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new SpredditException("User not found with name: " + username));
        user.setEnabled(true);
        userRepository.save(user);
    }
}
