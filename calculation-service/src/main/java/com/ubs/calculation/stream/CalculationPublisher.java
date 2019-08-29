package com.ubs.calculation.stream;

import com.ubs.calculation.model.Calculation;

public interface CalculationPublisher {

    void sendCalculationEvent(Calculation calculation);
}
