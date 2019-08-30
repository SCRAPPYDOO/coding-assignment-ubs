package com.ubs.proposal.service;

import com.ubs.proposal.dto.AttachCalculationDto;
import com.ubs.proposal.dto.CreateCalculationDto;
import com.ubs.proposal.exception.ProposalNotFoundException;
import com.ubs.proposal.model.*;
import com.ubs.proposal.stream.calculation.CalculationEvent;
import com.ubs.proposal.stream.calculation.CreateCalculationEvent;
import com.ubs.proposal.stream.calculation.CalculationPublisher;
import com.ubs.proposal.repository.ProposalRepository;
import com.ubs.proposal.stream.email.CreateEmailEvent;
import com.ubs.proposal.stream.email.EmailAttachment;
import com.ubs.proposal.stream.email.EmailPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProposalServiceImpl implements ProposalService {

    private final ProposalRepository proposalRepository;
    private final CalculationPublisher calculationPublisher;
    private final EmailPublisher emailPublisher;
    private final PdfService pdfService;

    public ProposalServiceImpl(final ProposalRepository proposalRepository,
                               final CalculationPublisher calculationPublisher,
                               final EmailPublisher emailPublisher,
                               final PdfService pdfService) {
        this.proposalRepository = proposalRepository;
        this.calculationPublisher = calculationPublisher;
        this.emailPublisher = emailPublisher;
        this.pdfService = pdfService;
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
        calculation.setPdfDocumentPath(calculationEvent.getPdfDocumentPath());
        calculation.setCalculationStatus(CalculationStatus.NOT_ATTACHED);

        proposal.getCalculationList().add(calculation);
    }

    @Override
    public void sendEmail(final Long proposalId) {
        final Proposal proposal = findProposalById(proposalId);

        //ToDo: add builder
        final CreateEmailEvent createEmailEvent = new CreateEmailEvent();

        final List<EmailAttachment> emailAttachmentListl = new ArrayList<>();

        if(!proposal.getProposalPdfDocumentList().isEmpty()) {
            emailAttachmentListl.add(new EmailAttachment(proposal.getProposalPdfDocumentList()
                    .stream()
                    .sorted(Comparator.comparing(ProposalPdfDocument::getId))
                    .findFirst()
                    .map(ProposalPdfDocument::getPath)
                    .get()
            ));
        }

        createEmailEvent.setClientId(proposal.getClientId());
        createEmailEvent.setAttachmentList(emailAttachmentListl);

        emailPublisher.createEmailEvent(createEmailEvent);
    }

    @Override
    @Transactional
    public void attachCalculation(Long proposalId, Long calculationId, AttachCalculationDto attachCalculationDto) {
        final Proposal proposal = findProposalById(proposalId);

        proposal.getCalculationList().stream()
                .filter(calculation -> calculation.getId().equals(calculationId))
                .findFirst()
                .ifPresent(calculation -> calculation.setCalculationStatus(attachCalculationDto.isAttach() ? CalculationStatus.ATTACHED : CalculationStatus.NOT_ATTACHED));
    }

    @Override
    public Proposal getProposalById(Long proposalId) {
        return findProposalById(proposalId);
    }

    @Override
    @Transactional
    public Proposal createPdf(Long proposalId, boolean attachCalculations) {
        final Proposal proposal = findProposalById(proposalId);

        final ProposalPdfDocument proposalPdfDocument = pdfService.createPdf(proposal, attachCalculations).orElseThrow(RuntimeException::new);

        proposalPdfDocument.setProposal(proposal);

        proposal.getProposalPdfDocumentList().add(proposalPdfDocument);

        return proposal;
    }

    private Proposal findProposalById(final Long proposalId) {
        return proposalRepository.findById(proposalId).orElseThrow(() -> new ProposalNotFoundException(String.format("proposal with id %s not found", proposalId)));
    }
}
