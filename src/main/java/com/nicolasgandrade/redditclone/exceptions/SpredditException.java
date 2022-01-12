package com.nicolasgandrade.redditclone.exceptions;

import org.springframework.mail.MailException;

public class SpredditException extends RuntimeException {
    public SpredditException(String s) {
        super(s);
    }
}
