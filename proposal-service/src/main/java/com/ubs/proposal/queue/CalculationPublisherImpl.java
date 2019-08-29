package com.ubs.proposal.queue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CalculationPublisherImpl implements CalculationPublisher {

    private final CalculationStream calculationStream;

    public CalculationPublisherImpl(CalculationStream calculationStream) {
        this.calculationStream = calculationStream;
    }

    @Override
    public void sendCreateCalculationEvent(final CreateCalculationEvent createCalculationEvent) {
        try {
            calculationStream.output()
                    .send(MessageBuilder.withPayload(createCalculationEvent).build());
        } catch (Exception ex) {
            log.error("Error publishing calculation request {} {}", createCalculationEvent.toString(), ex);
            //ToDo: add exception
        }
    }
}
