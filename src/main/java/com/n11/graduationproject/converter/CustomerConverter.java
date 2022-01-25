package com.n11.graduationproject.converter;

import com.n11.graduationproject.dto.customer.CustomerResponseDTO;
import com.n11.graduationproject.dto.customer.CustomerSaveRequestDTO;
import com.n11.graduationproject.dto.customer.CustomerUpdateRequestDTO;
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

    public static CustomerResponseDTO convertToCustomerResponseDTO(Customer customer) {

        CustomerResponseDTO customerResponseDTO = CustomerResponseDTO.builder()
                .id(customer.getId())
                .creationTime(customer.getCreationTime())
                .updateTime(customer.getUpdateTime())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .TCIdentificationNo(customer.getTCIdentificationNo())
                .birthDate(customer.getBirthDate())
                .phoneNumber(customer.getPhoneNumber())
                .build();

        if (customer.getLoanCustomer() != null) {
            LoanCustomer loanCustomer = customer.getLoanCustomer();

            customerResponseDTO.setSalary(loanCustomer.getSalary());
            customerResponseDTO.setAdditionalIncome(loanCustomer.getAdditionalIncome());
            customerResponseDTO.setSocialSecurityNo(loanCustomer.getSocialSecurityNo());
            customerResponseDTO.setTotalIncome(loanCustomer.getTotalIncome());
        }

        return customerResponseDTO;
    }
}