package com.n11.graduationproject.repository;

import com.n11.graduationproject.entity.CreditScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CreditScoreRepository extends JpaRepository<CreditScore, Long> {

    @Query("SELECT cs.score FROM CreditScore cs WHERE cs.TCIdentificationNo = :TCIdentificationNo")
    Optional<Long> getScoreByTCIdentificationNo(String TCIdentificationNo);
}