package com.n11.graduationproject.dto.customer;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
@Setter
public class LoanCustomerSaveUpdateRequestDTO {
    /**
     * LoanCustomer fields
     */
    @Digits(integer = 10, fraction = 2)
    @DecimalMin(value = "1.00")
    @NotNull(message = "Salary is cannot be null.")
    private BigDecimal salary;

    @Digits(integer = 10, fraction = 2)
    @DecimalMin(value = "0.00")
    @NotNull(message = "Additional income cannot be null.")
    private BigDecimal additionalIncome;

    @Size(min = 13, max = 13, message = "Social security no must be exact 13 character.")
    @NotBlank(message = "Social security no is mandatory.")
    @Pattern(regexp = "^\\d+$", message = "Social security no must include only numbers.")
    private String socialSecurityNo;
}
