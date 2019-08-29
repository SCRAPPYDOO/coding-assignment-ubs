package com.ubs.calculation.service;

import com.ubs.calculation.stream.CalculationPublisher;
import com.ubs.calculation.stream.CreateCalculationEvent;
import com.ubs.calculation.model.Calculation;
import com.ubs.calculation.repository.CalculationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CalculationServiceImpl implements CalculationService {

    private final CalculationRepository calculationRepository;
    private final CalculationPublisher calculationPublisher;

    public CalculationServiceImpl(CalculationRepository calculationRepository, CalculationPublisher calculationPublisher) {
        this.calculationRepository = calculationRepository;
        this.calculationPublisher = calculationPublisher;
    }

    @Transactional
    public void createCalculation(CreateCalculationEvent createCalculationEvent) {
        //ToDo: add logic

        final Calculation calculation = new Calculation();

        //ToDo: Add builder
        calculation.setClientId(createCalculationEvent.getClientId());
        calculation.setProposalId(createCalculationEvent.getProposalId());

        calculationRepository.save(calculation);

        calculationPublisher.sendCalculationEvent(calculation);
    }
}
