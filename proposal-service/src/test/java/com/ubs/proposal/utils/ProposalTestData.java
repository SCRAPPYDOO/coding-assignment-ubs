package com.ubs.proposal.utils;

import com.ubs.proposal.model.Proposal;
import com.ubs.proposal.model.ProposalPdfDocument;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProposalTestData {

    public static Proposal getProposal() {
        final Proposal proposal = new Proposal();
        proposal.setClientId(2L);
        proposal.setId(1L);

        final List<ProposalPdfDocument> proposalPdfDocumentList = new ArrayList<>();

        final ProposalPdfDocument proposalPdfDocument1 = new ProposalPdfDocument();
        proposalPdfDocument1.setProposal(proposal);
        proposalPdfDocument1.setPath("path1");
        proposalPdfDocument1.setLocalDateTime(LocalDateTime.of(1, 2, 3, 4, 2));
        proposalPdfDocument1.setId(2L);

        final ProposalPdfDocument proposalPdfDocument2 = new ProposalPdfDocument();
        proposalPdfDocument2.setProposal(proposal);
        proposalPdfDocument2.setPath("path2");
        proposalPdfDocument2.setLocalDateTime(LocalDateTime.of(1, 2, 3, 4, 4));
        proposalPdfDocument2.setId(1L);

        proposalPdfDocumentList.add(proposalPdfDocument1);
        proposalPdfDocumentList.add(proposalPdfDocument2);

        proposal.setProposalPdfDocumentList(proposalPdfDocumentList);

        return proposal;
    }
}
