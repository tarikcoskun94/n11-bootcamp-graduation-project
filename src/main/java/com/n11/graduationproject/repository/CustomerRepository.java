package com.n11.graduationproject.repository;

import com.n11.graduationproject.entity.Customer;
import com.n11.graduationproject.repository.customized.CustomizedRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface CustomerRepository extends CustomizedRepository<Customer, Long> {

    Optional<Customer> findByIdAndTCIdentificationNo(Long id, String TCIdentificationNo);

    Optional<Customer> findByTCIdentificationNoAndBirthDate(String TCIdentificationNo, LocalDate birthDate);

    boolean existsByTCIdentificationNo(String TCIdentificationNo);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumberAndIdNot(String phoneNumber, Long id);
}