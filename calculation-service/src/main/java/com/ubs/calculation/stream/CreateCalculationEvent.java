package com.ubs.calculation.stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CreateCalculationEvent {

    private final Long clientId;

    private final Long proposalId;

    @JsonCreator
    public CreateCalculationEvent(@JsonProperty("clientId") final Long clientId,
                                  @JsonProperty("proposalId") final Long proposalId) {
        this.clientId = clientId;
        this.proposalId = proposalId;
    }
}
