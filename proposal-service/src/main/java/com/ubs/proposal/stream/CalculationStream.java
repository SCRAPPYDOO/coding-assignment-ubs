package com.ubs.proposal.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface CalculationStream {

    String OUTPUT = "calculation-out-topic";

    @Output(OUTPUT)
    MessageChannel output();

    String INPUT = "calculation-in-topic";

    @Input(INPUT)
    SubscribableChannel input();
}
