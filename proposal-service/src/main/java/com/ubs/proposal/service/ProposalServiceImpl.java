package com.ubs.proposal.service;

import com.ubs.proposal.dto.CreateCalculationDto;
import com.ubs.proposal.exception.ProposalNotFoundException;
import com.ubs.proposal.model.Calculation;
import com.ubs.proposal.model.Proposal;
import com.ubs.proposal.stream.calculation.CalculationEvent;
import com.ubs.proposal.stream.calculation.CreateCalculationEvent;
import com.ubs.proposal.stream.calculation.CalculationPublisher;
import com.ubs.proposal.repository.ProposalRepository;
import com.ubs.proposal.stream.email.CreateEmailEvent;
import com.ubs.proposal.stream.email.EmailAttachment;
import com.ubs.proposal.stream.email.EmailPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class ProposalServiceImpl implements ProposalService {

    private final ProposalRepository proposalRepository;
    private final CalculationPublisher calculationPublisher;
    private final EmailPublisher emailPublisher;

    public ProposalServiceImpl(final ProposalRepository proposalRepository,
                               final CalculationPublisher calculationPublisher,
                               final EmailPublisher emailPublisher) {
        this.proposalRepository = proposalRepository;
        this.calculationPublisher = calculationPublisher;
        this.emailPublisher = emailPublisher;
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

        calculationPublisher.createCalculationEvent(createCalculationEvent);
    }

    @Override
    @Transactional
    public void addCalculation(final CalculationEvent calculationEvent) {

        final Proposal proposal = findProposalById(calculationEvent.getProposalId());

        //ToDo: add builder
        final Calculation calculation = new Calculation();
        calculation.setId(calculationEvent.getId());
        calculation.setProposal(proposal);

        proposal.getCalculationList().add(calculation);
    }

    @Override
    public void sendEmail(final Long proposalId) {
        final Proposal proposal = findProposalById(proposalId);

        //ToDo: add builder
        final CreateEmailEvent createEmailEvent = new CreateEmailEvent();

        //ToDo: add logic to create attachments
        final List<EmailAttachment> emailAttachmentListl = Collections.emptyList();

        createEmailEvent.setClientId(proposal.getClientId());
        createEmailEvent.setAttachmentList(emailAttachmentListl);

        emailPublisher.createEmailEvent(createEmailEvent);
    }

    private Proposal findProposalById(final Long proposalId) {
        return proposalRepository.findById(proposalId).orElseThrow(() -> new ProposalNotFoundException(String.format("proposal with id %s not found", proposalId)));
    }
}
