package com.ubs.proposal.service;

import com.ubs.proposal.model.CalculationStatus;
import com.ubs.proposal.model.Proposal;
import com.ubs.proposal.model.ProposalPdfDocument;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@Slf4j
public class PdfServiceImpl implements PdfService {

    private final String proposalPdfMainPath;
    private final String calculationPdfMainPath;

    public PdfServiceImpl(@Value("${pdf-service.proposal.path}") final String proposalPdfMainPath,
                          @Value("${pdf-service.calculation.path}") final String calculationPdfMainPath) {
        this.proposalPdfMainPath = proposalPdfMainPath;
        this.calculationPdfMainPath = calculationPdfMainPath;
    }

    @Override
    public Optional<ProposalPdfDocument> createPdf(final Proposal proposal, final boolean attachCalculations) {
        try {
            final PDDocument document = new PDDocument();
            final PDPage page = new PDPage();
            final PDFMergerUtility PDFmerger = new PDFMergerUtility();

            document.addPage(page);

            final PDPageContentStream contentStream = new PDPageContentStream(document, page);

            contentStream.setFont(PDType1Font.COURIER, 16);
            contentStream.beginText();
            contentStream.newLineAtOffset(200,685);
            contentStream.showText(String.format("Proposal Id: %s for Client Id: %s", proposal.getId(), proposal.getClientId()));
            contentStream.endText();
            contentStream.close();

            final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");

            final LocalDateTime generationTime = LocalDateTime.now();
            final String path = String.format("%sproposal_%s_%s_%s.pdf", proposalPdfMainPath, proposal.getId(), proposal.getClientId(), generationTime.format(formatter));

            final ProposalPdfDocument proposalPdfDocument = new ProposalPdfDocument();

            proposalPdfDocument.setLocalDateTime(generationTime);
            proposalPdfDocument.setPath(path);

            if(attachCalculations && !proposal.getCalculationList().isEmpty()) {
                proposal.getCalculationList().stream()
                        .filter(calculation -> CalculationStatus.ATTACHED.equals(calculation.getCalculationStatus()))
                        .forEach(calculation -> {
                            try {
                                File file = new File(calculation.getPdfDocumentPath());
                                final PDDocument calculationPdf = PDDocument.load(file);
                                PDFmerger.appendDocument(document, calculationPdf);
                            } catch (IOException e) {
                                log.error("Error merging calculation {} for proposal {} client {}", calculation.getId(), proposal.getId(), proposal.getClientId(), e);
                            }
                        });
            }

            document.save(path);
            document.close();

            return Optional.of(proposalPdfDocument);
        } catch (IOException e) {
            log.error("Error creating pdf document for proposal {} client {}", proposal.getId(), proposal.getClientId(), e);
        }

        return Optional.empty();
    }
}
