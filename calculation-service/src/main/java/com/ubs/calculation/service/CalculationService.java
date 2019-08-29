package com.ubs.calculation.service;

import com.ubs.calculation.stream.CreateCalculationEvent;
import com.ubs.calculation.model.Calculation;

public interface CalculationService {

    void createCalculation(CreateCalculationEvent createCalculationEvent);
}
