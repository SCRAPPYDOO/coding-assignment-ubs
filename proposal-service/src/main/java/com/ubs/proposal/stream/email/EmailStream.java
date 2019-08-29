package com.ubs.proposal.stream.email;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface EmailStream {

    String OUTPUT = "email-out-topic";

    @Output(OUTPUT)
    MessageChannel output();
}
