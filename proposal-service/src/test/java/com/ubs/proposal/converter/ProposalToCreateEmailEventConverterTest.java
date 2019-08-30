package com.ubs.proposal.converter;

import com.ubs.proposal.model.Proposal;
import com.ubs.proposal.stream.email.CreateEmailEvent;
import com.ubs.proposal.utils.ProposalTestData;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProposalToCreateEmailEventConverterTest {

    private ProposalToCreateEmailEventConverter proposalToCreateEmailEventConverter;

    @Before
    public void before() {
        proposalToCreateEmailEventConverter = new ProposalToCreateEmailEventConverter();
    }

    @Test
    public void shouldCreateEmailEventWithLatestPdf() {
        final Proposal proposal = ProposalTestData.getProposal();

        final CreateEmailEvent result = proposalToCreateEmailEventConverter.convert(proposal);

        assertEquals(result.getAttachmentList().size(), 1);
        assertEquals(result.getAttachmentList().get(0).getPath(), "path2");
    }
}
