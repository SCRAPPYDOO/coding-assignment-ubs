package com.ubs.proposal.stream;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CreateCalculationEvent {

    private Long clientId;

    private Long proposalId;
}
