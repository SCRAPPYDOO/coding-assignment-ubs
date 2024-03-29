package com.ubs.calculation.stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CalculationEvent {

    private final Long id;

    private final Long clientId;

    private final Long proposalId;

    private final String pdfDocumentPath;

    @JsonCreator
    public CalculationEvent(@JsonProperty("id") final Long id,
                            @JsonProperty("clientId") final Long clientId,
                            @JsonProperty("proposalId") final Long proposalId,
                            @JsonProperty("pdfDocumentPath") final String pdfDocumentPath) {
        this.id = id;
        this.clientId = clientId;
        this.proposalId = proposalId;
        this.pdfDocumentPath = pdfDocumentPath;
    }
}
