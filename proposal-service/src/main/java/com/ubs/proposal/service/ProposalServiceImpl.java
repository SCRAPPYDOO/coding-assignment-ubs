package com.ubs.proposal.service;

import com.ubs.proposal.dto.CreateCalculationDto;
import com.ubs.proposal.model.Proposal;
import com.ubs.proposal.queue.CalculationEvent;
import com.ubs.proposal.queue.CreateCalculationEvent;
import com.ubs.proposal.queue.CalculationPublisher;
import com.ubs.proposal.repository.ProposalRepository;
import org.springframework.stereotype.Service;

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
    public void createProposalCalculation(Long proposalId, CreateCalculationDto createCalculationDto) {

        CreateCalculationEvent createCalculationEvent = new CreateCalculationEvent();

        calculationPublisher.sendCreateCalculationEvent(createCalculationEvent);
    }

    @Override
    public void addCalculation(CalculationEvent calculationEvent) {

    }
}
