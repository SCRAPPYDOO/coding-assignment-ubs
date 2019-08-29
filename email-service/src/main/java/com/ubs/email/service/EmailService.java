package com.ubs.email.service;

import com.ubs.email.dto.CreateEmailEvent;

public interface EmailService {
    void sendEmail(CreateEmailEvent createEmailEvent);
}
