package com.ubs.proposal.service;

import com.ubs.proposal.converter.ProposalToCreateEmailEventConverter;
import com.ubs.proposal.model.Proposal;
import com.ubs.proposal.repository.ProposalRepository;
import com.ubs.proposal.stream.calculation.CalculationPublisher;
import com.ubs.proposal.stream.email.CreateEmailEvent;
import com.ubs.proposal.stream.email.EmailPublisher;
import com.ubs.proposal.utils.ProposalTestData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProposalServiceTest {

    private ProposalService proposalService;

    private ProposalRepository proposalRepository;
    private CalculationPublisher calculationPublisher;
    private EmailPublisher emailPublisher;
    private PdfService pdfService;
    private ProposalToCreateEmailEventConverter proposalToCreateEmailEventConverter;

    @Before
    public void before() {
        proposalRepository = mock(ProposalRepository.class);
        calculationPublisher = mock(CalculationPublisher.class);
        emailPublisher = mock(EmailPublisher.class);
        pdfService = mock(PdfService.class);
        proposalToCreateEmailEventConverter = mock(ProposalToCreateEmailEventConverter.class);

        proposalService = new ProposalServiceImpl(proposalRepository, calculationPublisher, emailPublisher, pdfService, proposalToCreateEmailEventConverter);
    }

    @Test
    public void sendEmailShouldSendLatestPdfDocument() {
        final Long proposalId = 1L;

        final Proposal proposal = ProposalTestData.getProposal();
        final CreateEmailEvent createEmailEvent = mock(CreateEmailEvent.class);

        when(proposalToCreateEmailEventConverter.convert(proposal)).thenReturn(createEmailEvent);
        when(proposalRepository.findById(proposalId)).thenReturn(Optional.of(proposal));
        doNothing().when(emailPublisher).createEmailEvent(any(CreateEmailEvent.class));

        proposalService.sendEmail(proposalId);

        verify(proposalRepository).findById(proposalId);
        verify(emailPublisher).createEmailEvent(any(CreateEmailEvent.class));
        verify(proposalToCreateEmailEventConverter).convert(proposal);
    }
}
