package com.n11.graduationproject.api;

import com.n11.graduationproject.entity.CreditScore;
import com.n11.graduationproject.repository.CreditScoreRepository;
import com.n11.graduationproject.util.NumberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FakeCreditScoreAPI {

    private final CreditScoreRepository creditScoreRepository;

    public Optional<Long> getCreditScoreByTCKN(String TCKN) {

        return creditScoreRepository.getScoreByTCIdentificationNo(TCKN);
    }

    public void save(String TCKN) {

        if (!creditScoreRepository.existsByTCIdentificationNo(TCKN)) {

            CreditScore creditScore = new CreditScore();
            creditScore.setCreationTime(LocalDateTime.now());
            creditScore.setUpdateTime(LocalDateTime.now());
            creditScore.setScore(NumberUtil.generateRandomNumberBetween(1, 1900));
            creditScore.setTCIdentificationNo(TCKN);

            creditScoreRepository.save(creditScore);
        }
    }
}