package com.ubs.proposal.stream.calculation;

import com.ubs.proposal.service.ProposalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CalculationConsumer {

    private final ProposalService proposalService;


    public CalculationConsumer(ProposalService proposalService) {
        this.proposalService = proposalService;
    }

    @StreamListener(CalculationStream.INPUT)
    public void consumeCalculation(final CalculationEvent calculationEvent) {
        try {
            log.info("Receiving calculation {}", calculationEvent.toString());
            proposalService.addCalculation(calculationEvent);
        } catch (Exception ex) {
            log.error("Error processing calculation: {} {}", calculationEvent.toString(), ex);
        }
    }
}
