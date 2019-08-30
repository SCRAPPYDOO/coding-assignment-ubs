package com.ubs.proposal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@ToString
public class Calculation {

    @Id
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Proposal proposal;

    private CalculationStatus calculationStatus;

    private String pdfDocumentPath;
}
