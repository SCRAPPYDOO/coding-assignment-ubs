package com.ubs.proposal.service;

import com.ubs.proposal.dto.AttachCalculationDto;
import com.ubs.proposal.dto.CreateCalculationDto;
import com.ubs.proposal.model.Proposal;
import com.ubs.proposal.stream.calculation.CalculationEvent;

import java.util.List;

public interface ProposalService {

    Proposal createProposal(Proposal proposal);

    List<Proposal> getProposals();

    void createProposalCalculation(Long proposalId, CreateCalculationDto createCalculationDto);

    void addCalculation(CalculationEvent calculationEvent);

    void sendEmail(Long proposalId);

    void attachCalculation(Long proposalId, Long calculationId, AttachCalculationDto attachCalculationDto);

    Proposal getProposalById(Long proposalId);

    Proposal createPdf(Long proposalId, boolean attachCalculations);
}
