package com.ubs.calculation.service;

import com.ubs.calculation.model.CalculationPdfDocument;
import com.ubs.calculation.stream.CalculationEvent;
import com.ubs.calculation.stream.CalculationPublisher;
import com.ubs.calculation.stream.CreateCalculationEvent;
import com.ubs.calculation.model.Calculation;
import com.ubs.calculation.repository.CalculationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CalculationServiceImpl implements CalculationService {

    private final CalculationRepository calculationRepository;
    private final CalculationPublisher calculationPublisher;
    private final PdfService pdfService;

    public CalculationServiceImpl(final CalculationRepository calculationRepository,
                                  final CalculationPublisher calculationPublisher,
                                  final PdfService pdfService) {
        this.calculationRepository = calculationRepository;
        this.calculationPublisher = calculationPublisher;
        this.pdfService = pdfService;
    }

    @Transactional
    public void createCalculation(CreateCalculationEvent createCalculationEvent) {
        final Calculation calculation = new Calculation();

        //ToDo: Add builder
        calculation.setClientId(createCalculationEvent.getClientId());
        calculation.setProposalId(createCalculationEvent.getProposalId());

        calculationRepository.save(calculation);

        final CalculationPdfDocument calculationPdfDocument = pdfService.createPdf(calculation).orElseThrow(RuntimeException::new);

        calculation.setCalculationPdfDocument(calculationPdfDocument);

        final CalculationEvent calculationEvent = new CalculationEvent(
                calculation.getId(),
                calculation.getClientId(),
                calculation.getProposalId(),
                calculation.getCalculationPdfDocument().getPath()
        );

        calculationPublisher.sendCalculationEvent(calculationEvent);
    }
}
