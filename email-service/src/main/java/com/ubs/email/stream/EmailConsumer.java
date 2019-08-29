package com.ubs.email.stream;

import com.ubs.email.dto.CreateEmailEvent;
import com.ubs.email.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EmailConsumer {
    private final EmailService emailService;

    public EmailConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @StreamListener(EmailStream.INPUT)
    public void consumeEmail(final CreateEmailEvent createEmailEvent) {
        try {
            log.info("Receiving createEmailEvent {}", createEmailEvent.toString());
            emailService.sendEmail(createEmailEvent);
        } catch (Exception ex) {
            log.error("Error processing createEmailEvent: {} {}", createEmailEvent.toString(), ex);
        }
    }
}
