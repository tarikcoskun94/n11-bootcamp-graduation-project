package com.n11.graduationproject.converter;

import com.n11.graduationproject.dto.collateral.CollateralResponseDTO;
import com.n11.graduationproject.dto.loanapplication.LoanApplicationResponseDTO;
import com.n11.graduationproject.dto.loanapplication.LoanApplicationResultDTO;
import com.n11.graduationproject.entity.Collateral;
import com.n11.graduationproject.entity.LoanApplication;
import com.n11.graduationproject.entity.LoanCustomer;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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

    public static LoanApplicationResponseDTO convertToLoanApplicationResponseDTO(LoanApplication loanApplication) {

        List<Collateral> collateralList = loanApplication.getCollateralList();
        List<CollateralResponseDTO> collateralResponseDTOList = CollateralConverter.convertToCollateralResponseDTOList(collateralList);

        return LoanApplicationResponseDTO.builder()
                .id(loanApplication.getId())
                .creationTime(loanApplication.getCreationTime())
                .updateTime(loanApplication.getUpdateTime())
                .customerId(loanApplication.getLoanCustomer().getId())
                .limit(loanApplication.getLimit())
                .status(loanApplication.getStatus())
                .creditScore(loanApplication.getCreditScore())
                .totalIncome(loanApplication.getTotalIncome())
                .collateralList(collateralResponseDTOList)
                .totalCollateralPrice(loanApplication.getTotalCollateralPrice())
                .build();
    }
}
