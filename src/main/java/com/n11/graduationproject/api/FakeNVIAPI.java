package com.n11.graduationproject.api;

import com.n11.graduationproject.util.NumberUtil;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FakeNVIAPI {

    public Optional<String> verifyTCKN(String TCKN) {

        long verificationRate = NumberUtil.generateRandomNumberBetween(1, 100);

        if (verificationRate > 5) {
            return Optional.of("OK");
        } else if (verificationRate <= 5) {
            return Optional.of("NOTOK");
        } else {
            return Optional.empty();
        }
    }
}