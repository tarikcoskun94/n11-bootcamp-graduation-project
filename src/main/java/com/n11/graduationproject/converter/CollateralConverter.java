package com.n11.graduationproject.converter;

import com.n11.graduationproject.dto.collateral.CollateralResponseDTO;
import com.n11.graduationproject.dto.collateral.CollateralSaveRequestDTO;
import com.n11.graduationproject.entity.Collateral;
import com.n11.graduationproject.entity.LoanApplication;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public final class CollateralConverter {

    public static CollateralResponseDTO convertToCollateralResponseDTO(Collateral collateral) {

        return CollateralResponseDTO.builder()
                .id(collateral.getId())
                .price(collateral.getPrice())
                .type(collateral.getType())
                .build();
    }

    public static List<CollateralResponseDTO> convertToCollateralResponseDTOList(List<Collateral> collateralList) {

        List<CollateralResponseDTO> collateralResponseDTOList = new ArrayList<>();

        for (Collateral collateral : collateralList) {
            collateralResponseDTOList.add(
                    convertToCollateralResponseDTO(collateral)
            );
        }

        return collateralResponseDTOList;
    }

    public static Collateral convertToCollateral(CollateralSaveRequestDTO collateralSaveRequestDTO,
                                                 LoanApplication savedLoanApplication) {

        Collateral collateral = Collateral.builder()
                .price(collateralSaveRequestDTO.getPrice())
                .type(collateralSaveRequestDTO.getType())
                .loanApplication(savedLoanApplication)
                .build();
        collateral.setCreationTime(LocalDateTime.now());
        collateral.setUpdateTime(LocalDateTime.now());

        return collateral;
    }

    public static List<Collateral> convertToCollateralList(List<CollateralSaveRequestDTO> collateralSaveRequestDTOList,
                                                           LoanApplication savedLoanApplication) {

        List<Collateral> collateralList = new ArrayList<>();

        for (CollateralSaveRequestDTO collateralSaveRequestDTO : collateralSaveRequestDTOList) {
            collateralList.add(
                    convertToCollateral(collateralSaveRequestDTO, savedLoanApplication)
            );
        }

        return collateralList;
    }
}
