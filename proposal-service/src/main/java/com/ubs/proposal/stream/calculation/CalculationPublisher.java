package com.ubs.proposal.stream.calculation;

public interface CalculationPublisher {

    void createCalculationEvent(CreateCalculationEvent createCalculationEvent);
}
