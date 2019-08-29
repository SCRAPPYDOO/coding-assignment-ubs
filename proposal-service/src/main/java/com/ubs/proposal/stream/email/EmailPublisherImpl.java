package com.ubs.proposal.stream.email;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailPublisherImpl implements EmailPublisher {

    private final EmailStream emailStream;

    public EmailPublisherImpl(EmailStream emailStream) {
        this.emailStream = emailStream;
    }

    @Override
    public void createEmailEvent(final CreateEmailEvent createEmailEvent) {
        try {
            log.info("Publishing event CreateEmailEvent {}", createEmailEvent.toString());
            emailStream.output()
                    .send(MessageBuilder.withPayload(createEmailEvent).build());
        } catch (Exception ex) {
            log.error("Error publishing createEmailEvent {} {}", createEmailEvent.toString(), ex);
            //ToDo: add exception
        }
    }
}
