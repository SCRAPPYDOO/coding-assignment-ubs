package com.ubs.proposal.stream.email;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class CreateEmailEvent {

    private Long clientId;

    private List<EmailAttachment> attachmentList;
}
