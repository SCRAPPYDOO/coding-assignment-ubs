package com.ubs.proposal.stream;

public interface CalculationPublisher {

    void sendCreateCalculationEvent(CreateCalculationEvent createCalculationEvent);
}
