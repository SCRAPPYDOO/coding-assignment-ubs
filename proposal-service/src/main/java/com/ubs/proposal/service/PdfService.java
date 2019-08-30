package com.ubs.proposal.service;

import com.ubs.proposal.model.Proposal;
import com.ubs.proposal.model.ProposalPdfDocument;

import java.util.Optional;

public interface PdfService {

    Optional<ProposalPdfDocument> createPdf(Proposal proposal, boolean attachCalculations);
}
