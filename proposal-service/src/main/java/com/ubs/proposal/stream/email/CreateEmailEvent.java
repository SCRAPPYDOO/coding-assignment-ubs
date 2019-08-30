package com.ubs.proposal.stream.email;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class CreateEmailEvent {

    private final Long clientId;
    private final List<EmailAttachment> attachmentList;

    private CreateEmailEvent(Long clientId, List<EmailAttachment> attachmentList) {
        this.clientId = clientId;
        this.attachmentList = attachmentList;
    }

    public static final class CreateEmailEventBuilder {
        private Long clientId;
        private List<EmailAttachment> attachmentList;

        public CreateEmailEventBuilder setClientId(Long clientId) {
            this.clientId = clientId;
            return this;
        }

        public CreateEmailEventBuilder setAttachmentList(List<EmailAttachment> attachmentList) {
            this.attachmentList = attachmentList;
            return this;
        }

        public CreateEmailEvent build() {
            return new CreateEmailEvent(clientId, attachmentList);
        }
    }
}
