package com.ubs.proposal.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Proposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long clientId;

    @OneToMany(mappedBy = "proposal", cascade = CascadeType.ALL)
    private List<Calculation> calculationList;

    @OneToMany(mappedBy = "proposal", cascade = CascadeType.ALL)
    private List<ProposalPdfDocument> proposalPdfDocumentList;
}
