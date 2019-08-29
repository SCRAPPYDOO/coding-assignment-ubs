package com.ubs.proposal.service;

import com.ubs.proposal.model.Proposal;
import com.ubs.proposal.repository.ProposalRepository;
import org.springframework.stereotype.Service;

@Service
public class ProposalServiceImpl implements ProposalService {

    private final ProposalRepository proposalRepository;

    public ProposalServiceImpl(ProposalRepository proposalRepository) {
        this.proposalRepository = proposalRepository;
    }

    public Proposal createProposal(final Proposal proposal) {
        return proposalRepository.save(proposal);
    }
}
