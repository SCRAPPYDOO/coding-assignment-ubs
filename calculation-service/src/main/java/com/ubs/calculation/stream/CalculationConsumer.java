package com.ubs.calculation.stream;

import com.ubs.calculation.service.CalculationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CalculationConsumer {

    private final CalculationService calculationService;

    public CalculationConsumer(CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @StreamListener(CalculationStream.INPUT)
    public void consumeCalculation(final CreateCalculationEvent createCalculationEvent) {
        try {
            log.info("Receiving create calculation event {}", createCalculationEvent.toString());
            calculationService.createCalculation(createCalculationEvent);
        } catch (Exception ex) {
            log.error("Error processing create calculation event: {} {}", createCalculationEvent.toString(), ex);
        }
    }
}
