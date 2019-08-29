package com.ubs.calculation.stream;

import lombok.Data;

@Data
public class CreateCalculationEvent {

    private final Long clientId;

    private final Long proposalId;
}
