package com.ubs.email.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class EmailAttachment {

    private final String path;

    @JsonCreator
    public EmailAttachment(@JsonProperty("path") String path) {
        this.path = path;
    }
}
