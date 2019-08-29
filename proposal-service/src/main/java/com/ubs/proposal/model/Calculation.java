package com.ubs.proposal.model;

import javax.persistence.*;

@Entity
public class Calculation {

    @Id
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Proposal proposal;
}
