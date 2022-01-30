package com.n11.graduationproject.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class FakeGSMAPI {

    public void sendSMS(String phoneNumber, String message, String signature) {

        String timeMessage = "--> Your message has been send [" + LocalDateTime.now().toString() + "].";
        String fullMessage = "\n" + message + "\n" + signature;

        log.info(timeMessage + fullMessage);
    }
}