package com.ubs.email.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class CreateEmailEvent {

    private final Long clientId;

    private final List<EmailAttachment> attachmentList;

    @JsonCreator
    public CreateEmailEvent(@JsonProperty("clientId") final Long clientId,
                            @JsonProperty("attachmentList") final List<EmailAttachment> attachmentList) {
        this.clientId = clientId;
        this.attachmentList = attachmentList;
    }
}
