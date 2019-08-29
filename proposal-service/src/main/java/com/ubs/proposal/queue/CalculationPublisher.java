package com.ubs.proposal.queue;

public interface CalculationPublisher {

    void sendCreateCalculationEvent(CreateCalculationEvent createCalculationEvent);
}
