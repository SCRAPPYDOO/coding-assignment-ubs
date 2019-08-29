package com.ubs.proposal.stream.email;

public interface EmailPublisher {

    void createEmailEvent(final CreateEmailEvent createEmailEvent);
}
