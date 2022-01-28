package com.n11.graduationproject.dto.loanapplication;

import com.n11.graduationproject.dto.Responsable;
import com.n11.graduationproject.enum_.LoanApplicationStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class LoanApplicationResultDTO implements Responsable {

    private BigDecimal limit;
    private LoanApplicationStatus status;
}
