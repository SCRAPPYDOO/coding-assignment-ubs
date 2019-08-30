package com.ubs.calculation.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Calculation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long clientId;

    private Long proposalId;

    @OneToOne(cascade = CascadeType.ALL)
    private CalculationPdfDocument calculationPdfDocument;
}
