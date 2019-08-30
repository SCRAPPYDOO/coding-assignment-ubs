package com.ubs.proposal.stream.calculation;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CreateCalculationEvent {

    private final Long clientId;

    private final Long proposalId;

    private CreateCalculationEvent(Long clientId, Long proposalId) {
        this.clientId = clientId;
        this.proposalId = proposalId;
    }

    public static final class CreateCalculationEventBuilder {
        private Long clientId;
        private Long proposalId;

        public CreateCalculationEventBuilder setClientId(Long clientId) {
            this.clientId = clientId;
            return this;
        }

        public CreateCalculationEventBuilder setProposalId(Long proposalId) {
            this.proposalId = proposalId;
            return this;
        }

        public CreateCalculationEvent build() {
            return new CreateCalculationEvent(clientId, proposalId);
        }
    }
}
