package com.n11.graduationproject.service;

import com.n11.graduationproject.api.FakeCreditScoreAPI;
import com.n11.graduationproject.converter.CustomerConverter;
import com.n11.graduationproject.dto.customer.CustomerResponseDTO;
import com.n11.graduationproject.dto.customer.CustomerSaveRequestDTO;
import com.n11.graduationproject.dto.customer.CustomerUpdateRequestDTO;
import com.n11.graduationproject.entity.Customer;
import com.n11.graduationproject.entity.LoanCustomer;
import com.n11.graduationproject.exception.customer.CustomerAlreadyExistingException;
import com.n11.graduationproject.exception.customer.CustomerNotFoundException;
import com.n11.graduationproject.exception.customer.LoanCustomerAlreadyExistingException;
import com.n11.graduationproject.repository.CustomerRepository;
import com.n11.graduationproject.repository.LoanCustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final LoanCustomerRepository loanCustomerRepository;
    private final FakeCreditScoreAPI fakeCreditScoreAPI;

    @Transactional
    public CustomerResponseDTO findById(Long id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer is not found by ID: " + id));

        return CustomerConverter.convertToCustomerResponseDTO(customer);
    }

    @Transactional
    public Customer findByIdAsEntity(Long id) {

        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer is not found by ID: " + id));
    }

    @Transactional
    public CustomerResponseDTO save(CustomerSaveRequestDTO customerSaveRequestDTO) {

        /** Checks whether request fields are used by another. */
        List<String> errorMessages = new ArrayList<>();

        if (customerRepository.existsByTCIdentificationNo(customerSaveRequestDTO.getTCIdentificationNo())) {
            errorMessages.add("TC identification no is already exist: " + customerSaveRequestDTO.getTCIdentificationNo());
        }
        if (customerRepository.existsByPhoneNumber(customerSaveRequestDTO.getPhoneNumber())) {
            errorMessages.add("Phone number is already exist: " + customerSaveRequestDTO.getPhoneNumber());
        }

        /** If any error message available, throw exception. */
        if (!errorMessages.isEmpty()) {
            throw new CustomerAlreadyExistingException(errorMessages);
        }

        fakeCreditScoreAPI.save(customerSaveRequestDTO.getTCIdentificationNo());

        Customer customer = CustomerConverter.convertToCustomer(customerSaveRequestDTO);
        Customer savedCustomer = customerRepository.saveAndFlush(customer);

        LoanCustomer loanCustomer = CustomerConverter.convertToLoanCustomer(customerSaveRequestDTO, savedCustomer);
        if (loanCustomer != null) {
            this.saveLoanCustomer(loanCustomer);
        }
        customerRepository.refresh(savedCustomer);

        return CustomerConverter.convertToCustomerResponseDTO(savedCustomer);
    }

    private LoanCustomer saveLoanCustomer(LoanCustomer loanCustomer) {

        String socialSecurityNo = loanCustomer.getSocialSecurityNo();
        Long customerId = loanCustomer.getCustomer().getId();

        if (loanCustomerRepository.existsBySocialSecurityNoAndIdNot(socialSecurityNo, customerId)) {
            throw new LoanCustomerAlreadyExistingException("Social security no is already exist: " + socialSecurityNo);
        }

        return loanCustomerRepository.saveAndFlush(loanCustomer);
    }

    @Transactional
    public CustomerResponseDTO update(CustomerUpdateRequestDTO customerUpdateRequestDTO) {

        if (!customerRepository.existsById(customerUpdateRequestDTO.getId())) {
            throw new CustomerNotFoundException("Customer is not found by ID: " + customerUpdateRequestDTO.getId());
        }
        if (customerRepository.existsByPhoneNumberAndIdNot(customerUpdateRequestDTO.getPhoneNumber(), customerUpdateRequestDTO.getId())) {
            throw new CustomerAlreadyExistingException("Phone number is already exist: " + customerUpdateRequestDTO.getPhoneNumber());
        }

        Customer customer = CustomerConverter.convertToCustomer(customerUpdateRequestDTO);
        Customer updatedCustomer = customerRepository.saveAndFlush(customer);

        LoanCustomer loanCustomer = CustomerConverter.convertToLoanCustomer(customerUpdateRequestDTO, updatedCustomer, loanCustomerRepository);
        if (loanCustomer != null) {
            this.saveLoanCustomer(loanCustomer);
        }
        customerRepository.refresh(updatedCustomer);

        return CustomerConverter.convertToCustomerResponseDTO(updatedCustomer);
    }

    @Transactional
    public CustomerResponseDTO deleteById(Long id) {

        CustomerResponseDTO customerResponseDTO = this.findById(id);

        customerRepository.deleteById(id);

        return customerResponseDTO;
    }
}