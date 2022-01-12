package com.nicolasgandrade.redditclone.service;

import com.nicolasgandrade.redditclone.exceptions.SpredditException;
import com.nicolasgandrade.redditclone.model.NotificationEmail;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

@Service
@AllArgsConstructor
@Slf4j
class MailService {

    private final JavaMailSender mailSender;
    private final MailContentBuilder mailContentBuilder;

    @Async
    void sendMail(NotificationEmail notificationEmail) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("confirm@spreddit.com");
            messageHelper.setTo(notificationEmail.getRecipient());
            messageHelper.setSubject(notificationEmail.getSubject());
            messageHelper.setText(notificationEmail.getBody());
        };
        try {
            log.info("Sending activation email");
            mailSender.send(messagePreparator);
            log.info("Activation email sent");
        } catch (MailException e) {
            log.error("Exception occurred when sending mail", e);
            throw new SpredditException("Exception occurred when sending mail to " + notificationEmail.getRecipient());
        }
    }

}
