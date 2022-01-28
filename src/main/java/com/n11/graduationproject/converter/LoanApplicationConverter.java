package com.n11.graduationproject.converter;

import com.n11.graduationproject.dto.loanapplication.LoanApplicationResultDTO;
import com.n11.graduationproject.entity.LoanApplication;
import com.n11.graduationproject.entity.LoanCustomer;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public final class LoanApplicationConverter {

    public static LoanApplication convertToLoanApplication(Long customerId, Long creditScore, BigDecimal totalIncome,
                                                           LoanApplicationResultDTO loanApplicationResultDTO) {

        LoanCustomer loanCustomer = new LoanCustomer();
        loanCustomer.setId(customerId);

        LoanApplication loanApplication = LoanApplication.builder()
                .limit(loanApplicationResultDTO.getLimit())
                .status(loanApplicationResultDTO.getStatus())
                .creditScore(creditScore)
                .totalIncome(totalIncome)
                .build();
        loanApplication.setLoanCustomer(loanCustomer);
        loanApplication.setCreationTime(LocalDateTime.now());
        loanApplication.setUpdateTime(LocalDateTime.now());

        return loanApplication;
    }
}
