package com.n11.graduationproject.dto.loanapplication;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
public class LoanApplicationCancelRequestDTO {

    @Size(min = 11, max = 11, message = "TC identification no must be exact 11 character.")
    @NotBlank(message = "TC identification no is mandatory.")
    @Pattern(regexp = "^\\d+$", message = "TC identification no must include only numbers.")
    private String TCIdentificationNo;

    @NotNull(message = "Birth date is mandatory.")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthDate;
}
