package com.n11.graduationproject.api;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class FakeGSMAPI {

    public void sendSMS(String phoneNumber, String message, String signature) {

        String timeMessage = "--> Your message has been send [" + LocalDateTime.now().toString() + "].";
        String fullMessage = "\n" + message + "\n" + signature;

        System.out.println("\n" + timeMessage + fullMessage + "\n");
    }
}