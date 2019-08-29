package com.ubs.proposal.service;

import com.ubs.proposal.dto.CreateCalculationDto;
import com.ubs.proposal.model.Calculation;
import com.ubs.proposal.model.Proposal;
import com.ubs.proposal.stream.CalculationEvent;
import com.ubs.proposal.stream.CreateCalculationEvent;
import com.ubs.proposal.stream.CalculationPublisher;
import com.ubs.proposal.repository.ProposalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProposalServiceImpl implements ProposalService {

    private final ProposalRepository proposalRepository;
    private final CalculationPublisher calculationPublisher;

    public ProposalServiceImpl(ProposalRepository proposalRepository, CalculationPublisher calculationPublisher) {
        this.proposalRepository = proposalRepository;
        this.calculationPublisher = calculationPublisher;
    }

    @Override
    public Proposal createProposal(final Proposal proposal) {
        return proposalRepository.save(proposal);
    }

    @Override
    public List<Proposal> getProposals() {
        return proposalRepository.findAll();
    }

    @Override
    public void createProposalCalculation(final Long proposalId, final CreateCalculationDto createCalculationDto) {

        //ToDo: add logic
        //ToDo: add builder
        //ToDo: client id ? for now fake as i don't have any context

        final CreateCalculationEvent createCalculationEvent = new CreateCalculationEvent();

        createCalculationEvent.setProposalId(proposalId);
        createCalculationEvent.setClientId(1L);

        calculationPublisher.sendCreateCalculationEvent(createCalculationEvent);
    }

    @Override
    @Transactional
    public void addCalculation(final CalculationEvent calculationEvent) {

        proposalRepository.findById(calculationEvent.getProposalId()).ifPresent(proposal -> {

            //ToDo: add builder
            //ToDo: exception if not found
            final Calculation calculation = new Calculation();
            calculation.setId(calculationEvent.getId());
            calculation.setProposal(proposal);

            proposal.getCalculationList().add(calculation);
        });
    }
}
