package com.ubs.calculation.stream;

public interface CalculationPublisher {

    void sendCalculationEvent(CalculationEvent calculationEvent);
}
