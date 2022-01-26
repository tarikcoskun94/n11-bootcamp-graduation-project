package com.n11.graduationproject.service;

import com.n11.graduationproject.api.FakeCreditScoreAPI;
import com.n11.graduationproject.converter.CustomerConverter;
import com.n11.graduationproject.dto.customer.CustomerResponseDTO;
import com.n11.graduationproject.dto.customer.CustomerSaveRequestDTO;
import com.n11.graduationproject.dto.customer.CustomerUpdateRequestDTO;
import com.n11.graduationproject.entity.CreditScore;
import com.n11.graduationproject.entity.Customer;
import com.n11.graduationproject.exception.customer.CustomerAlreadyExistingException;
import com.n11.graduationproject.exception.customer.CustomerNotFoundException;
import com.n11.graduationproject.repository.CustomerRepository;
import com.n11.graduationproject.util.NumberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final FakeCreditScoreAPI fakeCreditScoreAPI;

    @Transactional
    public CustomerResponseDTO findById(Long id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer is not found by ID: " + id));

        CustomerResponseDTO customerResponseDTO = CustomerConverter.convertToCustomerResponseDTO(customer);

        return customerResponseDTO;
    }

    @Transactional
    public Customer findByIdAsEntity(Long id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer is not found by ID: " + id));

        return customer;
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
        Customer savedCustomer = customerRepository.save(customer);
        CustomerResponseDTO customerResponseDTO = CustomerConverter.convertToCustomerResponseDTO(savedCustomer);

        return customerResponseDTO;
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
        customerRepository.refresh(updatedCustomer);
        CustomerResponseDTO customerResponseDTO = CustomerConverter.convertToCustomerResponseDTO(updatedCustomer);

        return customerResponseDTO;
    }

    @Transactional
    public CustomerResponseDTO deleteById(Long id) {

        CustomerResponseDTO customerResponseDTO = this.findById(id);

        customerRepository.deleteById(id);

        return customerResponseDTO;
    }
}