package com.ubs.proposal.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Proposal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long clientId;

    @OneToMany(mappedBy = "proposal", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Calculation> calculationList;
}
