package com.n11.graduationproject.converter;

import com.n11.graduationproject.dto.customer.*;
import com.n11.graduationproject.entity.Customer;
import com.n11.graduationproject.entity.LoanCustomer;

import java.time.LocalDateTime;

public final class CustomerConverter {

    public static Customer convertToCustomer(CustomerSaveRequestDTO customerSaveRequestDTO) {

        Customer customer = Customer.builder()
                .firstName(customerSaveRequestDTO.getFirstName())
                .lastName(customerSaveRequestDTO.getLastName())
                .TCIdentificationNo(customerSaveRequestDTO.getTCIdentificationNo())
                .birthDate(customerSaveRequestDTO.getBirthDate())
                .phoneNumber(customerSaveRequestDTO.getPhoneNumber())
                .build();

        customer.setCreationTime(LocalDateTime.now());
        customer.setUpdateTime(LocalDateTime.now());

        return customer;
    }

    public static Customer convertToCustomer(CustomerUpdateRequestDTO customerUpdateRequestDTO) {

        Customer customer = Customer.builder()
                .firstName(customerUpdateRequestDTO.getFirstName())
                .lastName(customerUpdateRequestDTO.getLastName())
                .birthDate(customerUpdateRequestDTO.getBirthDate())
                .phoneNumber(customerUpdateRequestDTO.getPhoneNumber())
                .build();

        customer.setId(customerUpdateRequestDTO.getId());
        customer.setUpdateTime(LocalDateTime.now());

        return customer;
    }

    public static LoanCustomer convertToLoanCustomer(LoanCustomerSaveUpdateRequestDTO loanCustomerSaveUpdateRequestDTO,
                                                     Customer customer) {

        LoanCustomer loanCustomer = null;

        if (loanCustomerSaveUpdateRequestDTO != null) {
            loanCustomer = LoanCustomer.builder()
                    .customer(customer)
                    .salary(loanCustomerSaveUpdateRequestDTO.getSalary())
                    .additionalIncome(loanCustomerSaveUpdateRequestDTO.getAdditionalIncome())
                    .socialSecurityNo(loanCustomerSaveUpdateRequestDTO.getSocialSecurityNo())
                    .build();
        }

        return loanCustomer;
    }

    public static CustomerResponseDTO convertToCustomerResponseDTO(Customer customer) {

        LoanCustomerResponseDTO loanCustomerResponseDTO = null;

        LoanCustomer loanCustomer = customer.getLoanCustomer();

        if (loanCustomer != null) {
            loanCustomerResponseDTO = convertToLoanCustomerResponseDTO(loanCustomer);
        }

        CustomerResponseDTO customerResponseDTO = CustomerResponseDTO.builder()
                .id(customer.getId())
                .creationTime(customer.getCreationTime())
                .updateTime(customer.getUpdateTime())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .TCIdentificationNo(customer.getTCIdentificationNo())
                .birthDate(customer.getBirthDate())
                .phoneNumber(customer.getPhoneNumber())
                .loanCustomer(loanCustomerResponseDTO)
                .build();

        return customerResponseDTO;
    }

    public static LoanCustomerResponseDTO convertToLoanCustomerResponseDTO(LoanCustomer loanCustomer) {

        return LoanCustomerResponseDTO.builder()
                .salary(loanCustomer.getSalary())
                .additionalIncome(loanCustomer.getAdditionalIncome())
                .socialSecurityNo(loanCustomer.getSocialSecurityNo())
                .totalIncome(loanCustomer.getTotalIncome())
                .build();
    }
}