package com.ubs.email.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.xml.internal.ws.api.message.Attachment;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class CreateEmailEvent {

    private final Long clientId;

    private final List<Attachment> attachmentList;

    @JsonCreator
    public CreateEmailEvent(@JsonProperty("clientId") final Long clientId,
                            @JsonProperty("attachmentList") final List<Attachment> attachmentList) {
        this.clientId = clientId;
        this.attachmentList = attachmentList;
    }
}
