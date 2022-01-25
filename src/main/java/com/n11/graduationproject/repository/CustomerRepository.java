package com.n11.graduationproject.repository;

import com.n11.graduationproject.entity.Customer;
import com.n11.graduationproject.repository.customized.CustomizedRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CustomizedRepository<Customer, Long> {

    boolean existsByTCIdentificationNo(String TCIdentificationNo);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumberAndIdNot(String phoneNumber, Long id);
}