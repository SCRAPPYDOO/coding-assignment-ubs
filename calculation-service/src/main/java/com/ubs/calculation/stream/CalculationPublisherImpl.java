package com.ubs.calculation.stream;

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
    public void sendCalculationEvent(final CalculationEvent calculationEvent) {
        try {
            calculationStream.output()
                    .send(MessageBuilder.withPayload(calculationEvent).build());
        } catch (Exception ex) {
            log.error("Error publishing new calculation event {} {}", calculationEvent.toString(), ex);
        }
    }
}
