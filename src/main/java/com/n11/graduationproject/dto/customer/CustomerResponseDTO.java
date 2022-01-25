package com.n11.graduationproject.dto.customer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.n11.graduationproject.dto.Responsable;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder
public class CustomerResponseDTO implements Responsable {

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
    private String firstName;
    private String lastName;
    private String TCIdentificationNo;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthDate;
    private String phoneNumber;

    /**
     * LoanCustomer fields
     */
    private BigDecimal salary;
    private BigDecimal additionalIncome;
    private String socialSecurityNo;
    private BigDecimal totalIncome;
}