package com.n11.graduationproject.repository;

import com.n11.graduationproject.entity.CreditScore;
import com.n11.graduationproject.repository.customized.CustomizedRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreditScoreRepository extends CustomizedRepository<CreditScore, Long> {

    @Query("SELECT cs.score FROM CreditScore cs WHERE cs.TCIdentificationNo = :TCIdentificationNo")
    Optional<Long> getScoreByTCIdentificationNo(String TCIdentificationNo);

    boolean existsByTCIdentificationNo(String TCIdentificationNo);
}