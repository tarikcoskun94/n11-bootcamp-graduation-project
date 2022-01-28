package com.n11.graduationproject.dto.loanapplication;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.n11.graduationproject.dto.Responsable;
import com.n11.graduationproject.dto.collateral.CollateralResponseDTO;
import com.n11.graduationproject.dto.customer.LoanCustomerResponseDTO;
import com.n11.graduationproject.enum_.LoanApplicationStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class LoanApplicationResponseDTO implements Responsable {

    /**
     * Base fields
     */
    private Long id;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", timezone = "Europe/Istanbul")
    private LocalDateTime creationTime;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", timezone = "Europe/Istanbul")
    private LocalDateTime updateTime;

    /**
     * Customer fields
     */
    private Long customerId;

    /**
     * LoanApplication fields
     */
    private BigDecimal limit;
    private LoanApplicationStatus status;
    private Long creditScore;
    private BigDecimal totalIncome;
    private List<CollateralResponseDTO> collateralList;
    private BigDecimal totalCollateralPrice;
}
