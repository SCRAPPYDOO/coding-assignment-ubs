package com.ubs.email.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface EmailStream {
    String INPUT = "email-in-topic";

    @Input(INPUT)
    SubscribableChannel input();
}
