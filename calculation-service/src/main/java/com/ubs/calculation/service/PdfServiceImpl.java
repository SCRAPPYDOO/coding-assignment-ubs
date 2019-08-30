package com.ubs.calculation.service;

import com.ubs.calculation.model.Calculation;
import com.ubs.calculation.model.CalculationPdfDocument;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@Slf4j
public class PdfServiceImpl implements PdfService {

    private final String calculationPdfMainPath;

    public PdfServiceImpl(@Value("${pdf-service.calculation.path}") final String calculationPdfMainPath) {
        this.calculationPdfMainPath = calculationPdfMainPath;
    }

    @Override
    public Optional<CalculationPdfDocument> createPdf(final Calculation calculation) {
        try {
            final PDDocument document = new PDDocument();
            final PDPage page = new PDPage();

            document.addPage(page);

            final PDPageContentStream contentStream = new PDPageContentStream(document, page);

            contentStream.setFont(PDType1Font.COURIER, 16);
            contentStream.beginText();
            contentStream.newLineAtOffset(200,685);
            contentStream.showText(String.format("Calculation Id: %s for Proposal Id and Client Id: %s", calculation.getId(), calculation.getProposalId(), calculation.getClientId()));
            contentStream.endText();
            contentStream.close();

            final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");

            final LocalDateTime generationTime = LocalDateTime.now();
            final String path = String.format("%scalculation_%s_%s_%s_%s.pdf", calculationPdfMainPath, calculation.getId(), calculation.getProposalId(), calculation.getClientId(), generationTime.format(formatter));

            final CalculationPdfDocument calculationPdfDocument = new CalculationPdfDocument();

            calculationPdfDocument.setLocalDateTime(generationTime);
            calculationPdfDocument.setPath(path);

            document.save(path);
            document.close();

            return Optional.of(calculationPdfDocument);
        } catch (IOException e) {
            log.error("Error creating pdf document for calculation {} proposal {} client {}", calculation.getId(), calculation.getProposalId(), calculation.getClientId(), e);
        }

        return Optional.empty();
    }
}
