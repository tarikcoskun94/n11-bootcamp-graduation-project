package com.n11.graduationproject.dto.customer;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class LoanCustomerResponseDTO {

    /**
     * LoanCustomer fields
     */
    private BigDecimal salary;
    private BigDecimal additionalIncome;
    private String socialSecurityNo;
    private BigDecimal totalIncome;
}
