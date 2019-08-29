package com.ubs.calculation.stream;

import com.ubs.calculation.model.Calculation;
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
    public void sendCalculationEvent(final Calculation calculation) {
        try {
            calculationStream.output()
                    .send(MessageBuilder.withPayload(calculation).build());
        } catch (Exception ex) {
            log.error("Error publishing new calculation event {} {}", calculation.toString(), ex);
            //ToDo: add exception
        }
    }
}
