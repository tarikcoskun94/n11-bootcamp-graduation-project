package com.n11.graduationproject.dto.customer;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
public class CustomerUpdateRequestDTO {

    /**
     * Base fields
     */
    private Long id;

    /**
     * Customer fields
     */
    @Size(min = 1, max = 50, message = "First name must be maximum 50 character.")
    @NotBlank(message = "First name is mandatory.")
    private String firstName;

    @Size(min = 1, max = 50, message = "Last name must be maximum 50 character.")
    @NotBlank(message = "Last name is mandatory.")
    private String lastName;

    @NotNull(message = "Birth date is mandatory.")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthDate;

    @Size(min = 10, max = 10, message = "Phone number must be exact 10 character.")
    @NotBlank(message = "Phone number is mandatory.")
    @Pattern(regexp = "^\\d+$", message = "Phone number must include only numbers.")
    private String phoneNumber;

    @Valid
    private LoanCustomerSaveUpdateRequestDTO loanCustomer;
}