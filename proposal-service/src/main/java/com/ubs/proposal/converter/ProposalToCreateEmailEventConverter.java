package com.ubs.proposal.converter;

import com.ubs.proposal.model.Proposal;
import com.ubs.proposal.model.ProposalPdfDocument;
import com.ubs.proposal.stream.email.CreateEmailEvent;
import com.ubs.proposal.stream.email.EmailAttachment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class ProposalToCreateEmailEventConverter {

    public CreateEmailEvent convert(final Proposal proposal) {

        final List<EmailAttachment> emailAttachmentListl = new ArrayList<>();

        if(!proposal.getProposalPdfDocumentList().isEmpty()) {
            emailAttachmentListl.add(new EmailAttachment(proposal.getProposalPdfDocumentList()
                    .stream()
                    .max(Comparator.comparing(ProposalPdfDocument::getLocalDateTime))
                    .map(ProposalPdfDocument::getPath)
                    .get()
            ));
        }

        return new CreateEmailEvent.CreateEmailEventBuilder()
                .setClientId(proposal.getClientId())
                .setAttachmentList(emailAttachmentListl)
                .build();
    }
}
