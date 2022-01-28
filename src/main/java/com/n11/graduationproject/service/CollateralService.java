package com.n11.graduationproject.service;

import com.n11.graduationproject.converter.CollateralConverter;
import com.n11.graduationproject.dto.collateral.CollateralSaveRequestDTO;
import com.n11.graduationproject.entity.Collateral;
import com.n11.graduationproject.entity.LoanApplication;
import com.n11.graduationproject.repository.CollateralRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CollateralService {

    private final CollateralRepository collateralRepository;

    public List<Collateral> saveAll(List<CollateralSaveRequestDTO> collateralSaveRequestDTOList, LoanApplication savedLoanApplication) {

        List<Collateral> collateralList
                = CollateralConverter.convertToCollateralList(collateralSaveRequestDTOList, savedLoanApplication);

        return collateralRepository.saveAllAndFlush(collateralList);
    }
}
