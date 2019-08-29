package com.ubs.proposal.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CreateProposalDto {

    private final Long clientId;

    @JsonCreator
    public CreateProposalDto(@JsonProperty("clientId") final Long clientId) {
        this.clientId = clientId;
    }
}
