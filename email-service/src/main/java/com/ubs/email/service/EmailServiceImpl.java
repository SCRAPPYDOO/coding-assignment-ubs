package com.ubs.email.service;

import com.ubs.email.dto.CreateEmailEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    @Override
    public void sendEmail(CreateEmailEvent createEmailEvent) {
        log.info("Sending email for email event {}", createEmailEvent.toString());
    }
}
