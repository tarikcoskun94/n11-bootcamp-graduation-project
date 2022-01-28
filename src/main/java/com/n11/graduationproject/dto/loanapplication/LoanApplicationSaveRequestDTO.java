package com.n11.graduationproject.dto.loanapplication;

import com.n11.graduationproject.dto.collateral.CollateralSaveRequestDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class LoanApplicationSaveRequestDTO {

    /**
     * Customer fields
     */
    private Long customerId;

    @Size(min = 11, max = 11, message = "TC identification no must be exact 11 character.")
    @NotBlank(message = "TC identification no is mandatory.")
    @Pattern(regexp = "^\\d+$", message = "TC identification no must include only numbers.")
    private String customerTCIdentificationNo;

    /**
     * LoanApplication fields
     */
    @Valid
    private List<CollateralSaveRequestDTO> collateralList;
}
