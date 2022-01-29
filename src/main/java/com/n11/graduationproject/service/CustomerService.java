package com.n11.graduationproject.service;

import com.n11.graduationproject.api.FakeCreditScoreAPI;
import com.n11.graduationproject.converter.CustomerConverter;
import com.n11.graduationproject.dto.customer.CustomerResponseDTO;
import com.n11.graduationproject.dto.customer.CustomerSaveRequestDTO;
import com.n11.graduationproject.dto.customer.CustomerUpdateRequestDTO;
import com.n11.graduationproject.dto.customer.LoanCustomerSaveUpdateRequestDTO;
import com.n11.graduationproject.entity.Customer;
import com.n11.graduationproject.entity.LoanCustomer;
import com.n11.graduationproject.exception.customer.CustomerAlreadyExistingException;
import com.n11.graduationproject.exception.customer.CustomerNotFoundException;
import com.n11.graduationproject.exception.customer.LoanCustomerAlreadyExistingException;
import com.n11.graduationproject.exception.customer.LoanCustomerNotFoundException;
import com.n11.graduationproject.repository.CustomerRepository;
import com.n11.graduationproject.repository.LoanCustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final LoanCustomerRepository loanCustomerRepository;
    private final FakeCreditScoreAPI fakeCreditScoreAPI;

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

        LoanCustomerSaveUpdateRequestDTO loanCustomerSaveUpdateRequestDTO = customerSaveRequestDTO.getLoanCustomer();
        LoanCustomer loanCustomer = CustomerConverter.convertToLoanCustomer(loanCustomerSaveUpdateRequestDTO, savedCustomer);

        if (loanCustomer != null) {
            this.persistLoanCustomer(loanCustomer);
        }

        customerRepository.refresh(savedCustomer);

        return CustomerConverter.convertToCustomerResponseDTO(savedCustomer);
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

        LoanCustomerSaveUpdateRequestDTO loanCustomerSaveUpdateRequestDTO = customerUpdateRequestDTO.getLoanCustomer();
        LoanCustomer loanCustomer = CustomerConverter.convertToLoanCustomer(loanCustomerSaveUpdateRequestDTO, updatedCustomer);

        if (loanCustomer != null) {
            if (loanCustomerRepository.existsById(updatedCustomer.getId())) {
                loanCustomer.setId(updatedCustomer.getId());
            }
            this.persistLoanCustomer(loanCustomer);
        }

        customerRepository.refresh(updatedCustomer);

        return CustomerConverter.convertToCustomerResponseDTO(updatedCustomer);
    }

    public List<CustomerResponseDTO> findAll() {

        List<Customer> customerList = customerRepository.findAll();

        return CustomerConverter.convertToCustomerResponseDTOList(customerList);
    }

    public CustomerResponseDTO findById(Long id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer is not found by ID: " + id));

        return CustomerConverter.convertToCustomerResponseDTO(customer);
    }

    @Transactional
    public CustomerResponseDTO deleteById(Long id) {

        CustomerResponseDTO customerResponseDTO = this.findById(id);

        customerRepository.deleteById(id);

        return customerResponseDTO;
    }


    /**
     * Private ones
     */
    private LoanCustomer persistLoanCustomer(LoanCustomer loanCustomer) {

        String socialSecurityNo = loanCustomer.getSocialSecurityNo();
        Long customerId = loanCustomer.getCustomer().getId();

        if (loanCustomerRepository.existsBySocialSecurityNoAndIdNot(socialSecurityNo, customerId)) {
            throw new LoanCustomerAlreadyExistingException("Social security no is already exist: " + socialSecurityNo);
        }

        return loanCustomerRepository.saveAndFlush(loanCustomer);
    }

    /**
     * Following functions is to serve internal others generally.
     */
    public Customer findByIdAndTCIdentificationNo(Long id, String TCIdentificationNo) {

        return customerRepository.findByIdAndTCIdentificationNo(id, TCIdentificationNo)
                .orElseThrow(() -> new CustomerNotFoundException("Customer is not found by ID and TC identification no: "
                        + id + " | " + TCIdentificationNo));
    }

    public Customer findByTCIdentificationNoAndBirthDate(String TCIdentificationNo, LocalDate birthDate) {

        return customerRepository.findByTCIdentificationNoAndBirthDate(TCIdentificationNo, birthDate)
                .orElseThrow(() -> new CustomerNotFoundException("Customer is not found by TC identification no and birth date: "
                        + TCIdentificationNo + " | " + birthDate));
    }

    public LoanCustomer getLoanCustomerByIdAndTCKN(Long id, String TCIdentificationNo) {

        Customer customer = this.findByIdAndTCIdentificationNo(id, TCIdentificationNo);

        LoanCustomer loanCustomer = customer.getLoanCustomer();
        if (loanCustomer == null) {
            throw new LoanCustomerNotFoundException("Loan customer is not found by ID and TC identification no: "
                    + id + " | " + TCIdentificationNo);
        }

        return loanCustomer;
    }

    public LoanCustomer getLoanCustomerByTCKNAndBirthDate(String TCIdentificationNo, LocalDate birthDate) {

        Customer customer = this.findByTCIdentificationNoAndBirthDate(TCIdentificationNo, birthDate);

        LoanCustomer loanCustomer = customer.getLoanCustomer();
        if (loanCustomer == null) {
            throw new LoanCustomerNotFoundException("Loan customer is not found by ID and TC identification no: "
                    + TCIdentificationNo + " | " + birthDate);
        }

        return loanCustomer;
    }
}