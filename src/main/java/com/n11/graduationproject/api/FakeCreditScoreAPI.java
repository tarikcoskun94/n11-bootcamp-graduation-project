package com.n11.graduationproject.api;

import com.n11.graduationproject.entity.CreditScore;
import com.n11.graduationproject.repository.CreditScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FakeCreditScoreAPI {

    private final CreditScoreRepository creditScoreRepository;

    public Optional<Long> getCreditScoreByTCKN(String TCKN) {

        return creditScoreRepository.getScoreByTCIdentificationNo(TCKN);
    }

    public CreditScore save(CreditScore creditScore) {

        // TODO: Aynı TCKN kayıtları için already exist kontrolleri burada da gerekebilir. Sonradan bir göz at.
        return creditScoreRepository.save(creditScore);
    }
}