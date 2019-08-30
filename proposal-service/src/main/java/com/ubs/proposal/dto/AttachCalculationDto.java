package com.ubs.proposal.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class AttachCalculationDto {
    private final boolean attach;

    @JsonCreator
    public AttachCalculationDto(@JsonProperty("attach") boolean attach) {
        this.attach = attach;
    }
}
