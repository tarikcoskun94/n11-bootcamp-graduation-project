package com.n11.graduationproject.util;

import com.n11.graduationproject.dto.collateral.CollateralSaveRequestDTO;

import java.math.BigDecimal;
import java.util.List;

public final class CollateralUtil {

    public static BigDecimal calculateTotalCollateralPrice(List<CollateralSaveRequestDTO> collateralList) {

        BigDecimal totalCollateralPrice = new BigDecimal("0.00");

        for (CollateralSaveRequestDTO collateralSaveRequestDTO : collateralList) {
            BigDecimal collateralPrice = collateralSaveRequestDTO.getPrice();
            totalCollateralPrice = totalCollateralPrice.add(collateralPrice);
        }

        return totalCollateralPrice;
    }
}
