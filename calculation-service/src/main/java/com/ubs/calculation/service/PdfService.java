package com.ubs.calculation.service;

import com.ubs.calculation.model.Calculation;
import com.ubs.calculation.model.CalculationPdfDocument;

import java.util.Optional;

public interface PdfService {

    Optional<CalculationPdfDocument> createPdf(Calculation calculation);
}
