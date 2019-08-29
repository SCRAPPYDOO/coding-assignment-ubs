package com.ubs.proposal.dto;

import com.ubs.proposal.model.Calculation;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class ProposalDto {

    private Long id;

    private Long clientId;

    private List<Calculation> calculationList;
}
